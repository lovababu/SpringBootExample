package com.avol.repository;

import com.avol.domain.ProjectDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Lovababu on 6/6/2015.
 */

@Repository
public interface ProjectRepository {

    String create(ProjectDomain projectDomain);

    ProjectDomain get(String id);

    String update(ProjectDomain projectDomain);

    void delete(String id);

    List<ProjectDomain> list();

}
