package com.avol.service.impl;

import com.avol.domain.ProjectDomain;
import com.avol.repository.ProjectRepository;
import com.avol.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Lovababu on 6/6/2015.
 */

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void create(ProjectDomain projectDomain) {
        projectRepository.create(projectDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectDomain get(String id) {
        return projectRepository.get(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String update(ProjectDomain projectDomain) {
        return projectRepository.update(projectDomain);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(ProjectDomain projectDomain) {
        projectRepository.delete(projectDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDomain> list() {
        return projectRepository.list();
    }
}
