package com.avol.service.impl;

import com.avol.domain.ProjectDomain;
import com.avol.repository.ProjectRepository;
import com.avol.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lovababu on 6/6/2015.
 */

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public String create(ProjectDomain projectDomain) {
        return projectRepository.create(projectDomain);
    }

    @Override
    public ProjectDomain get(String id) {
        return projectRepository.get(id);
    }

    @Override
    public String update(ProjectDomain projectDomain) {
        return projectRepository.update(projectDomain);
    }

    @Override
    public void delete(String id) {
        projectRepository.delete(id);
    }

    @Override
    public List<ProjectDomain> list() {
        return projectRepository.list();
    }
}
