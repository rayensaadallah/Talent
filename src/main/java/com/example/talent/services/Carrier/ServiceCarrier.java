package com.example.talent.services.Carrier;

import com.example.talent.dtos.CarrierDto;
import com.example.talent.Mappers.EntityMapper;
import com.example.talent.dtos.UserDto;
import com.example.talent.models.Carrier;
import com.example.talent.models.Users;
import com.example.talent.repository.CarrierRepository;
import com.example.talent.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ServiceCarrier implements IServiceCarrier{

    CarrierRepository carrierRepository;

    private EntityMapper<Carrier, CarrierDto> carrierDtoMapper;

    private UserRepository usersRepository;
    @Override
    public List<CarrierDto> getAllCarriers() {
        List<Carrier> carriers = carrierRepository.findAll();

        List<CarrierDto> carrierDtoList = new ArrayList<>();
        for (Carrier carrier : carriers) {
            CarrierDto carrierDto = carrierDtoMapper.fromBasic(carrier, CarrierDto.class);
            carrierDtoList.add(carrierDto);
        }
        return carrierDtoList;
    }
    @Override
    public CarrierDto getCarrier(Integer carrierId) {
        Carrier carrier = carrierRepository.findById(carrierId)
                .orElseThrow(() -> new EntityNotFoundException("Carrier with ID " + carrierId + " not found"));
        return carrierDtoMapper.fromBasic(carrier, CarrierDto.class);
    }

    @Override
    public void add(CarrierDto carrierDto) {
        Carrier carrierEntity = carrierDtoMapper.fromDTO(carrierDto, Carrier.class);
        carrierRepository.save(carrierEntity);
    }


    @Override
    public void delete(Integer id) {
        // Check if the carrier exists
        if (carrierRepository.existsById(id)) {
            // Delete the carrier by its ID
            carrierRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Carrier with ID " + id + " not found");
        }
    }

    @Override
    public void update(Integer id, CarrierDto carrierDto) {
        // Retrieve the existing carrier by ID
        Carrier existingCarrier = carrierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carrier with ID " + id + " not found"));

        // Update carrier attributes from CarrierDto
        existingCarrier.setTitle(carrierDto.getTitle());
        existingCarrier.setLevel(carrierDto.getLevel());
        existingCarrier.setNeeded_days(carrierDto.getNeeded_days());
        existingCarrier.setDate_Start(carrierDto.getDate_Start());
        existingCarrier.setType(carrierDto.getType());

        // Save the updated carrier entity
        carrierRepository.save(existingCarrier);
    }


    public void assignCarrierToUser(Integer carrierId, Integer userId) {
        Carrier carrier = carrierRepository.findById(carrierId).orElse(null);
        Users user = usersRepository.findById(userId).orElse(null);

        if (carrier != null && user != null) {
            user.setCarrier(carrier);
            usersRepository.save(user);
        } else {
            throw new EntityNotFoundException("Carrier or User not found");
        }
    }


}
