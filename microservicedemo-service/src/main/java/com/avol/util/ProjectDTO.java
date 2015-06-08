package com.avol.util;

import com.avol.api.Project;
import com.avol.domain.ProjectDomain;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Durga on 6/8/2015.
 */
public class ProjectDTO {

    public static ProjectDomain projectDomain(Project project){
        ProjectDomain projectDomain = new ProjectDomain();
        BeanUtils.copyProperties(project, projectDomain);
        return projectDomain;
    }

    public static Project projectApi(ProjectDomain projectDomain){
        Project project = new Project();
        BeanUtils.copyProperties(projectDomain, project);
        return project;
    }

    public static List<Project> projectList(List<ProjectDomain> projectDomains){
        List<Project> projects = null;
        if (!CollectionUtils.isEmpty(projectDomains)) {
            projects = new ArrayList<>();
            for (ProjectDomain projectDomain : projectDomains) {
                projects.add(projectApi(projectDomain));
            }
        }
        return projects;
    }
}
