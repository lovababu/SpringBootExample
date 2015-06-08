/**
 * Copyright 2000-2015 NeuStar, Inc. All rights reserved.
 * NeuStar, the Neustar logo and related names and logos are registered
 * trademarks, service marks or tradenames of NeuStar, Inc. All other
 * product names, company names, marks, logos and symbols may be trademarks
 * of their respective owners.
 */

package com.avol.controller;

import com.avol.api.Project;
import com.avol.api.ProjectServiceResponse;
import com.avol.domain.ProjectDomain;
import com.avol.service.ProjectService;
import com.avol.util.ProjectDTO;
import lombok.extern.slf4j.Slf4j;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project")
@Api(name = "Microservice demo Application on Project.", description = "Demo application using Spring boot.")
@ExposesResourceFor(ProjectServiceResponse.class)
@Slf4j
public class ProjectController {


    private final EntityLinks entityLinks;

    private final ProjectService projectService;

    @Autowired
    public ProjectController(EntityLinks entityLinks, ProjectService projectService){
        this.entityLinks = entityLinks;
        this.projectService = projectService;
    }


    //jsondoc config.
    @ApiMethod(consumes = MediaType.APPLICATION_JSON_VALUE,
            description = "Creates the project information.", responsestatuscode = "201", path = "/project")

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ApiResponseObject HttpEntity<Resource<ProjectServiceResponse>> create(@ApiBodyObject(clazz = Project.class)
                                              @RequestBody Project project) {

        projectService.create(ProjectDTO.projectDomain(project));
        //constructing response.
        ProjectServiceResponse response =ProjectServiceResponse.builder()
                .withHttpStatusCode(HttpStatus.CREATED.toString())
                .withSuccess(true)
                .withMessage("Request processed successfully.").build();
        Resource resource = new Resource<ProjectServiceResponse>(response);
        resource.add(entityLinks.linkForSingleResource(ProjectServiceResponse.class, project.getId()).withSelfRel());
        resource.add(entityLinks.linkToSingleResource(ProjectServiceResponse.class, "/list").withSelfRel());
        return new ResponseEntity<Resource<ProjectServiceResponse>>(resource, HttpStatus.CREATED);
    }


    //jsondoc config.
    @ApiMethod(produces = MediaType.APPLICATION_JSON_VALUE, description = "Returns Project information as JSON text.",
            responsestatuscode = "200", path = "/{id}")

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ApiResponseObject HttpEntity<Resource<ProjectServiceResponse>> get(@ApiPathParam(description = "Project id", format = "AnyString")
                @PathVariable("id") String id) {
        ProjectDomain projectDomain =  projectService.get(id);
        Project project = ProjectDTO.projectApi(projectDomain);
        //constructing response.
        ProjectServiceResponse response =ProjectServiceResponse.builder()
                .withHttpStatusCode(project != null ? HttpStatus.OK.toString() : HttpStatus.NOT_FOUND.toString())
                .withSuccess(true)
                .withProject(project)
                .withMessage("Request processed successfully.").build();
        Resource resource = new Resource<ProjectServiceResponse>(response);
        resource.add(entityLinks.linkToCollectionResource(ProjectServiceResponse.class).withSelfRel());
        return new ResponseEntity<Resource<ProjectServiceResponse>>(resource, HttpStatus.OK);
    }

    //jsondoc config.
    @ApiMethod(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE,
            description = "Updates the project information.", responsestatuscode = "200", path = "/{id}")

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ApiResponseObject HttpEntity<Resource<ProjectServiceResponse>> update(@ApiPathParam(description = "Project id", format = "AnyString")
                  @PathVariable("id") String id, @ApiBodyObject(clazz = Project.class) @RequestBody Project project) {
        projectService.update(ProjectDTO.projectDomain(project));

        //constructing response.
        ProjectServiceResponse response =ProjectServiceResponse.builder()
                .withHttpStatusCode(HttpStatus.OK.toString())
                .withSuccess(true)
                .withProject(project)
                .withMessage("Request processed successfully.").build();
        Resource resource = new Resource<ProjectServiceResponse>(response);
        resource.add(entityLinks.linkToSingleResource(ProjectServiceResponse.class, project.getId()).withSelfRel());
        resource.add(entityLinks.linkToCollectionResource(ProjectServiceResponse.class).withSelfRel());
        return new ResponseEntity<Resource<ProjectServiceResponse>>(resource, HttpStatus.OK);
    }


    //jsondoc config.
    @ApiMethod(produces = MediaType.APPLICATION_JSON_VALUE, description = "Deletes Project information.",
            responsestatuscode = "200", path = "/{id}")

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ApiResponseObject HttpEntity<Resource<ProjectServiceResponse>> delete(@ApiPathParam(description = "Project id", format = "AnyString")
                  @PathVariable("id") String id) {
        ProjectDomain projectDomain = new ProjectDomain();
        projectDomain.setId(id);
        projectService.delete(projectDomain);
        //constructing response.
        ProjectServiceResponse response =ProjectServiceResponse.builder()
                .withHttpStatusCode(HttpStatus.OK.toString())
                .withSuccess(true)
                .withMessage("Request processed successfully.").build();
        Resource resource = new Resource<ProjectServiceResponse>(response);
        resource.add(entityLinks.linkToSingleResource(ProjectServiceResponse.class, "/list").withSelfRel());
        resource.add(entityLinks.linkToCollectionResource(ProjectServiceResponse.class).withSelfRel());
        return new ResponseEntity<Resource<ProjectServiceResponse>>(resource, HttpStatus.OK);
    }

    //jsondoc config.
    @ApiMethod(produces = MediaType.APPLICATION_JSON_VALUE, description = "Deletes Project information.",
            responsestatuscode = "200", path = "/list")

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ApiResponseObject HttpEntity<Resource<ProjectServiceResponse>> list() {
        List<ProjectDomain> projectDomains = projectService.list();

        //constructing response.
        ProjectServiceResponse response =ProjectServiceResponse.builder()
                .withHttpStatusCode(HttpStatus.OK.toString())
                .withSuccess(true)
                .withProject(ProjectDTO.projectList(projectDomains))
                .withMessage("Request processed successfully.").build();
        Resource resource = new Resource<ProjectServiceResponse>(response);
        resource.add(entityLinks.linkToCollectionResource(ProjectServiceResponse.class).withSelfRel());
        return new ResponseEntity<Resource<ProjectServiceResponse>>(resource, HttpStatus.OK);
    }
}
