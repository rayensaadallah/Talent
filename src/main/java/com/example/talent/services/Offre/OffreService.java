package com.example.talent.services.Offre;

import com.example.talent.Mappers.EntityMapper;
import com.example.talent.dtos.OffreDto;
import com.example.talent.models.Offer;
import com.example.talent.repository.OffreRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class OffreService implements IoffreService{

    OffreRepository offreRepository;

    private EntityMapper<Offer,OffreDto> offreentityMapper;

    @Override
    public List<OffreDto> getAlloffres() {
        List<Offer> offers = offreRepository.findAll();
        List<OffreDto> offredto = new ArrayList<>();
        for (Offer offer : offers) {
            offredto.add(offreentityMapper.fromBasic(offer,OffreDto.class));
        }
        return offredto;
    }


    @Override
    public void add(OffreDto offreDto) {
        Offer offer = offreentityMapper.fromDTO(offreDto, Offer.class);
        offreRepository.save(offer);
    }

    @Override
    public void delete(Integer id) {
        if (offreRepository.existsById(id)) {
            // Delete the carrier by its ID
            offreRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Offer with ID " + id + " not found");
        }
    }

    @Override
    public OffreDto update(Integer id, OffreDto updatedOffreDto) throws EntityNotFoundException {
        Offer existingOffer = offreRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Offer not found with id: " + id));
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
    public OffreDto getoneoffre(Integer id) {
        Offer carrier = offreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carrier with ID " + id + " not found"));
        return offreentityMapper.fromBasic(carrier, OffreDto.class);
    }
}
