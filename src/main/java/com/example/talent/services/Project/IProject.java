package com.example.talent.services.Project;


import com.example.talent.models.Project;

public interface IProject {
    public Project addproject (Project p);
    public Project updateProject (Project p);
    public Project findbyidProject (Integer idp);
    void deleteProject(Integer idV);
}
