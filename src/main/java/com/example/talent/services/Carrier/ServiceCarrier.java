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


    private EntityMapper<Users, UserDto> userDtoMapper;


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
    public void add(CarrierDto carrierDto) {
        // Map the CarrierDto to a Carrier entity
        Carrier carrierEntity = carrierDtoMapper.fromDTO(carrierDto, Carrier.class);
        // Save the Carrier entity to the repository
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
            UserDto userDto = userDtoMapper.fromBasic(user, UserDto.class);

            // Assign the carrier to the user DTO
            userDto.setCarrier(carrierDtoMapper.fromBasic(carrier, CarrierDto.class));

            // Now, you can decide whether you want to add this user to the list of users
            // in the CarrierDto or some other logic based on your requirements.

            // Convert the modified user DTO back to the Users entity and save
            Users updatedUser = userDtoMapper.fromDTO(userDto, Users.class);
            usersRepository.save(updatedUser);
        } else {
            throw new EntityNotFoundException("Carrier or User not found");
        }
    }

    @Override
    public CarrierDto getCarrier(Integer carrierId) {
        Carrier carrier = carrierRepository.findById(carrierId)
                .orElseThrow(() -> new EntityNotFoundException("Carrier with ID " + carrierId + " not found"));
        return carrierDtoMapper.fromBasic(carrier, CarrierDto.class);
    }
}
