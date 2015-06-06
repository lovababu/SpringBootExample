package com.avol.service;

import com.avol.domain.ProjectDomain;

import java.util.List;

/**
 * Created by Lovababu on 6/6/2015.
 */
public interface ProjectService {

    String create(ProjectDomain projectDomain);

    ProjectDomain get(String id);

    String update(ProjectDomain projectDomain);

    void delete(String id);

    List<ProjectDomain> list();
}
