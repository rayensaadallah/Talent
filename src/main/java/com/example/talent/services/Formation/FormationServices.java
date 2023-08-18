package com.example.talent.services.Formation;

import com.example.talent.Mappers.EntityMapper;
import com.example.talent.dtos.Formationdto;
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
@NoArgsConstructor
public class FormationServices implements IFormationServices {
    @Autowired
    FormationRepository formationRepository;
    @Autowired
    UserRepository userRepository;

    private EntityMapper<Formation,Formationdto> formationFormationdtoEntityMapper;

    UserService userService;
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
    public void delete(Integer id) {
        if (formationRepository.existsById(id)) {
            // Delete the carrier by its ID
            formationRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Formation with ID " + id + " not found");
        }

    }

    @Override
    public Formationdto update(Integer id, Formationdto updated) {
        Formation existing = formationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Formaiton not found with id: " + id));
        existing.setId(id);
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
    public Formationdto getone(Integer id) {
        Formation f = formationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carrier with ID " + id + " not found"));
        return formationFormationdtoEntityMapper.fromBasic(f, Formationdto.class);
    }

    @Override
    public void BuyFormation(Integer id) {
        Optional<Formation> optionalFormation = formationRepository.findById(id);
        Optional<Users> optionalUser = userRepository.findByUsername(userService.getUserByToken().getUsername());

        if (optionalFormation.isPresent() && optionalUser.isPresent()) {
            Formation formation = optionalFormation.get();
            Users user = optionalUser.get();

            formation.getUsersSet().add(user);
            user.getFormations().add(formation);

            formationRepository.save(formation);
            userRepository.save(user);
        } else {
            throw new EntityNotFoundException("Formation or user not found for the given id and username.");
        }
    }
}
