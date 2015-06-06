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
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/project")
@Api(name = "Microservice demo Application on Project.", description = "Demo application using Spring boot.")
@ExposesResourceFor(ProjectServiceResponse.class)
public class ProjectServiceController {

    private final EntityLinks entityLinks;

    @Autowired
    public ProjectServiceController(EntityLinks entityLinks){
        this.entityLinks = entityLinks;
    }


    //jsondoc config.
    @ApiMethod(consumes = MediaType.APPLICATION_JSON_VALUE,
            description = "Creates the project information.", responsestatuscode = "201", path = "/project")

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ApiResponseObject HttpEntity<Resource<ProjectServiceResponse>> create(@ApiBodyObject(clazz = Project.class)
                                              @RequestBody Project project) {
        System.out.println("ProjectServiceController.create...: " + project.getName());
        ProjectServiceResponse response =ProjectServiceResponse.builder()
                .withHttpStatusCode(HttpStatus.CREATED.toString())
                .withSuccess(true)
                .withMessage("Request processed successfully.").build();
        Resource resource = new Resource<ProjectServiceResponse>(response);
        resource.add(entityLinks.linkForSingleResource(ProjectServiceResponse.class, project.getId()).withSelfRel());
        return new ResponseEntity<Resource<ProjectServiceResponse>>(resource, HttpStatus.CREATED);
    }


    //jsondoc config.
    @ApiMethod(produces = MediaType.APPLICATION_JSON_VALUE, description = "Returns Project information as JSON text.",
            responsestatuscode = "200", path = "/{id}")

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ApiResponseObject Project get(@ApiPathParam(description = "Project id", format = "AnyString")
                @PathVariable("id") String id) {
        Project project = new Project();
        return project;
    }

    //jsondoc config.
    @ApiMethod(produces = MediaType.TEXT_HTML_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE,
            description = "Updates the project information.", responsestatuscode = "200", path = "/{id}")

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.TEXT_HTML_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ApiResponseObject String update(@ApiPathParam(description = "Project id", format = "AnyString")
                  @PathVariable("id") String id,
                  @ApiBodyObject(clazz = Project.class) @RequestBody Project project) {
        return "Request Processed successfully.";
    }


    //jsondoc config.
    @ApiMethod(produces = MediaType.TEXT_HTML_VALUE, description = "Deletes Project information.", responsestatuscode = "200", path = "/{id}")

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.TEXT_HTML_VALUE)
    public @ApiResponseObject String delete(@ApiPathParam(description = "Project id", format = "AnyString")
                  @PathVariable("id") String id) {
        return "Request Processed successfully.";
    }
}
