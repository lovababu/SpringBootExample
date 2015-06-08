package com.avol.repository.impl;

import com.avol.domain.ProjectDomain;
import com.avol.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Lovababu on 6/6/2015.
 */

@Repository
@Slf4j
public class ProjectRepositoryImpl implements ProjectRepository {

    public ProjectRepositoryImpl(){}

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(ProjectDomain projectDomain) {
        sessionFactory.getCurrentSession().save(projectDomain);
    }

    @Override
    public ProjectDomain get(String id) {
        return (ProjectDomain) sessionFactory.getCurrentSession().get(ProjectDomain.class, id);
    }

    @Override
    public String update(ProjectDomain projectDomain) {
        sessionFactory.getCurrentSession().saveOrUpdate(projectDomain);
        return projectDomain.getId();
    }

    @Override
    public void delete(ProjectDomain projectDomain) {
        sessionFactory.getCurrentSession().delete(projectDomain);
    }

    @Override
    public List<ProjectDomain> list() {
        return sessionFactory.getCurrentSession().createQuery("FROM com.avol.domain.ProjectDomain").list();
    }
}
