package com.example.talent.services.Carrier;

import com.example.talent.dtos.CarrierDto;
import com.example.talent.Mappers.EntityMapper;
import com.example.talent.dtos.Formationdto;
import com.example.talent.dtos.UserDto;
import com.example.talent.models.Carrier;
import com.example.talent.models.Formation;
import com.example.talent.models.Users;
import com.example.talent.repository.CarrierRepository;
import com.example.talent.repository.FormationRepository;
import com.example.talent.repository.UserRepository;
import com.example.talent.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceCarrier implements IServiceCarrier{

    CarrierRepository carrierRepository;
    UserRepository userRepository;
    UserService userService;
    private EntityMapper<Carrier, CarrierDto> carrierDtoMapper;

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
    public CarrierDto getCarrier(CarrierDto carrierDto) {
        Carrier carrier = carrierRepository.getByTitle(carrierDto.getTitle());
        return carrierDtoMapper.fromBasic(carrier, CarrierDto.class);
    }

    @Override
    public void add(CarrierDto carrierDto) {
        Carrier carrierEntity = carrierDtoMapper.fromDTO(carrierDto, Carrier.class);
        carrierRepository.save(carrierEntity);
    }


    @Override
    public void delete(Integer id) {
        carrierRepository.deleteById(id);
    }

    @Override
    public void update( CarrierDto carrierDto) {
        Carrier existingCarrier = carrierRepository.getByTitle(carrierDto.getTitle());
        // Update carrier attributes from CarrierDto
        existingCarrier.setTitle(carrierDto.getTitle());
        existingCarrier.setLevel(carrierDto.getLevel());
        existingCarrier.setNeeded_days(carrierDto.getNeeded_days());
        existingCarrier.setDate_Start(carrierDto.getDate_Start());
        existingCarrier.setType(carrierDto.getType());
        // Save the updated carrier entity
        carrierRepository.save(existingCarrier);
    }

    @Override
    public void BuyCarrier(CarrierDto carrierDto) {
        Carrier f =carrierRepository.getByTitle(carrierDto.getTitle());
        Optional<Users> optionalUser = userRepository.findByUsername(userService.getUserByToken().getUsername());
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            f.getUsers().add(user);
            carrierRepository.save(f);
            userRepository.save(user);
        } else {
            throw new EntityNotFoundException("Formation or user not found for the given id and username.");
        }
    }


}
