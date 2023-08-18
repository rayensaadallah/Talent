package com.example.talent.services.Skills;

import com.example.talent.models.Skills;
import com.example.talent.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Skills_ser implements ISkills {
    @Autowired
    SkillsRepository sr;
    @Override
    public Skills addSkills(Skills s) {
        return sr.save(s);
    }

    @Override
    public Skills updateSkills(Skills s) {
        return sr.save(s);
    }

    @Override
    public Skills findbyidSkills(Integer ids) {
        return sr.findById(ids).orElse(null);
    }

    @Override
    public void deleteSkills(Integer ids) {
sr.deleteById(ids);
    }
}
