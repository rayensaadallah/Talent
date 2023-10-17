package com.example.talent.controllers;

import com.example.talent.models.Cv;
import com.example.talent.models.Skills;
import com.example.talent.repository.CVRepository;
import com.example.talent.repository.SkillsRepository;
import com.example.talent.services.Skills.ISkills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Skills")
@CrossOrigin("*")

public class SkillsController {
    @Autowired
    ISkills is;
    @Autowired
    CVRepository cr;
    @Autowired
    SkillsRepository sr;

    @PostMapping("/addSkills")
    public Skills addSkills(@RequestBody Skills p){
        return is.addSkills(p);
    }
    @PutMapping("/updateSkills")
    public Skills updateProject(@RequestBody Skills p){
        return is.updateSkills(p);
    }
    @GetMapping("/retrieve-Skills/{Skills-id}")
    public Skills getSkills(@PathVariable("Skills-id") Integer idp) {

        return is.findbyidSkills(idp);
    }
    @DeleteMapping("/remove-Skills/{Skills-id}")
    public void removeSkills(@PathVariable("Skills-id") Integer idp) {
        is.deleteSkills(idp);
    }
    @GetMapping("/retrieve-by-user/{usrid}")
    public List<Skills> getSkillsbyuser(@PathVariable("usrid") Integer idp) {
        Cv cv=cr.findCvByUser_UserId(idp);
        return sr.findAllByCv(cv) ;
    }
}
