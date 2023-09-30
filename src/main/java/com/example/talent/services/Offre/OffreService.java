package com.example.talent.services.Offre;

import com.example.talent.Mappers.EntityMapper;
import com.example.talent.dtos.CarrierDto;
import com.example.talent.dtos.OffreDto;
import com.example.talent.models.Carrier;
import com.example.talent.models.Formation;
import com.example.talent.models.Offer;
import com.example.talent.models.Users;
import com.example.talent.repository.CarrierRepository;
import com.example.talent.repository.OffreRepository;
import com.example.talent.repository.UserRepository;
import com.example.talent.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OffreService implements IoffreService {

    OffreRepository offreRepository;
    UserService userService;
    UserRepository userRepository;
    CarrierRepository carrierRepository;

    private EntityMapper<Offer, OffreDto> offreDtoEntityMapper;

    @Override
    public List<OffreDto> getAlloffres() {
        List<Offer> offers = offreRepository.findAll();
        List<OffreDto> offredtoList = new ArrayList<>();
        for (Offer offer : offers) {
            OffreDto offreDto = offreDtoEntityMapper.fromBasic(offer, OffreDto.class);
            offredtoList.add(offreDto);
        }
        return offredtoList;
    }


    @Override
    public void add(OffreDto offreDto) {
        Offer offer = offreDtoEntityMapper.fromDTO(offreDto, Offer.class);
        offreRepository.save(offer);
    }

    @Override
    public void delete(OffreDto id) {
        Offer o = offreRepository.getByTitle(id.getTitle());
        if (offreRepository.existsById(o.getId())) {
            offreRepository.deleteById(o.getId());
        } else {
            throw new EntityNotFoundException("Offre with Title  " + o.getTitle() + " not found");
        }
    }


    @Override
    public OffreDto update(OffreDto updatedOffreDto) throws EntityNotFoundException {
        Offer existingOffer = offreRepository.getByTitle(updatedOffreDto.getTitle());
        existingOffer.setBackground(updatedOffreDto.getBackground());
        existingOffer.setDescription(updatedOffreDto.getDescription());
        existingOffer.setPlace(updatedOffreDto.getPlace());
        existingOffer.setCompany(updatedOffreDto.getCompany());
        existingOffer.setRequirement(updatedOffreDto.getRequirement());
        existingOffer.setSalary(updatedOffreDto.getSalary());
        existingOffer.setTitle(updatedOffreDto.getTitle());
        offreRepository.save(existingOffer);
        return updatedOffreDto;

    }

    @Override
    public OffreDto getOne(OffreDto offreDto) {
        Offer c = offreRepository.getByTitle(offreDto.getTitle());
        return offreDtoEntityMapper.fromBasic(c, OffreDto.class);
    }

    @Override
    public void buyoffre(OffreDto dto, CarrierDto carrierDto) {
        Offer f = offreRepository.getByTitle(dto.getTitle());
        Carrier c = carrierRepository.getByTitle(carrierDto.getTitle());
        f.getCarriers().add(c);
        offreRepository.save(f);
        System.out.println("done ");
    }
}


