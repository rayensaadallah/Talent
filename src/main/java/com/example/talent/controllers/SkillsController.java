package com.example.talent.controllers;

import com.example.talent.models.Skills;
import com.example.talent.services.Skills.ISkills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Skills")
public class SkillsController {
    @Autowired
    ISkills is;
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

}
