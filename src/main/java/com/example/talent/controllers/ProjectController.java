package com.example.talent.controllers;

import com.example.talent.models.Project;
import com.example.talent.services.Project.IProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Project")
public class ProjectController {
    @Autowired
    IProject ip;

    @PostMapping("/addProject")
    public Project addProject(@RequestBody Project p){
    return ip.addproject(p);
    }
    @PutMapping("/updateProject")
    public Project updateProject(@RequestBody Project p){
        return ip.updateProject(p);
    }
    @GetMapping("/retrieve-Project/{Project-id}")
    public Project getliv(@PathVariable("Project-id") Integer idp) {

        return ip.findbyidProject(idp);
    }
    @DeleteMapping("/remove-Project/{Project-id}")
    public void removeLiv(@PathVariable("Project-id") Integer idp) {
        ip.deleteProject(idp);
    }

}
