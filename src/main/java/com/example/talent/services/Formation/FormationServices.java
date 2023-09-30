package com.example.talent.services.Formation;

import com.example.talent.Mappers.EntityMapper;
import com.example.talent.dtos.Formationdto;
import com.example.talent.dtos.OffreDto;
import com.example.talent.models.Formation;
import com.example.talent.models.Users;
import com.example.talent.repository.FormationRepository;
import com.example.talent.repository.UserRepository;
import com.example.talent.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FormationServices implements IFormationServices {
    FormationRepository formationRepository;
    UserRepository userRepository;
    UserService userService;
    private EntityMapper<Formation,Formationdto> formationFormationdtoEntityMapper;


    @Override
    public List<Formationdto> getAll() {
        List<Formation> f = formationRepository.findAll();
        List<Formationdto> dto = new ArrayList<>();
        for (Formation fs : f) {
            dto.add(formationFormationdtoEntityMapper.fromBasic(fs,Formationdto.class));
        }
        return dto;
    }

    @Override
    public void add(Formationdto dto) {
        formationRepository.save(formationFormationdtoEntityMapper.fromDTO(dto, Formation.class));
    }

    @Override
    public void delete(Formationdto formationdto) {
        Formation f=formationRepository.getFormationByTitle(formationdto.getTitle());
        if (f!=null){
            formationRepository.deleteById(f.getId());
        } else {
            throw new EntityNotFoundException("Formation with  Title" + formationdto.getTitle() + " not found");
        }
    }

    @Override
    public Formationdto update(Formationdto updated) {
        Formation existing = formationRepository.getFormationByTitle(updated.getTitle());
        existing.setLanguage(updated.getLanguage());
        existing.setDescription(updated.getDescription());
        existing.setPlace(updated.getPlace());
        existing.setRate(updated.getRate());
        existing.setSkills(updated.getSkills());
        existing.setTitle(updated.getTitle());
        existing.setTechnologie(updated.getTechnologie());
        existing.setNbr_hours(updated.getNbr_hours());
        existing.setName_company(updated.getName_company());
        existing.setDescription(updated.getDescription());
        formationRepository.save(existing);
        return updated;
    }

    @Override
    public Formationdto getone(Formationdto formationdto) {
        Formation f=formationRepository.getFormationByTitle(formationdto.getTitle());
        return formationFormationdtoEntityMapper.fromBasic(f, Formationdto.class);
    }

    @Override
    public void BuyFormation(Formationdto formationdto) {
        Formation f = formationRepository.getFormationByTitle(formationdto.getTitle());
        Optional<Users> optionalUser = userRepository.findByUsername(userService.getUserByToken().getUsername());
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            f.getUsersSet().add(user);
            user.getFormations().add(f);
            formationRepository.save(f);
            userRepository.save(user);
        } else {
            throw new EntityNotFoundException("Formation or user not found for the given id and username.");
        }
    }
}
