package com.example.talent.services.Skills;

import com.example.talent.models.Skills;

public interface ISkills {
    public Skills addSkills (Skills s);
    public Skills updateSkills (Skills s);
    public Skills findbyidSkills (Integer ids);
    void deleteSkills(Integer ids);

}
