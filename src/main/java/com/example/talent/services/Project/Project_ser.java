package com.example.talent.services.Project;

import com.example.talent.models.Project;
import com.example.talent.repository.ProjectRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Project_ser implements IProject {
    @Autowired
    ProjectRepsitory pr;
    @Override
    public Project addproject(Project p) {
        return pr.save(p);
    }

    @Override
    public Project updateProject(Project p) {
        return pr.save(p);
    }

    @Override
    public Project findbyidProject(Integer idp) {
        return pr.findById(idp).orElse(null);
    }

    @Override
    public void deleteProject(Integer idp) {
pr.deleteById(idp);
    }
}
