package com.avol.repository;

import com.avol.domain.ProjectDomain;

import java.util.List;

/**
 * Created by Lovababu on 6/6/2015.
 */

public interface ProjectRepository {

    void create(ProjectDomain projectDomain);

    ProjectDomain get(String id);

    String update(ProjectDomain projectDomain);

    void delete(ProjectDomain projectDomain);

    List<ProjectDomain> list();

}
