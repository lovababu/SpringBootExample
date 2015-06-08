package com.avol.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Durga on 6/5/2015.
 */
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiObject(show = true, description = "Project json structure.")
public class ProjectServiceResponse{

    @ApiObjectField(description = "Gives you the status of the request.", required = true)
    private boolean success = false;
    @ApiObjectField(description = "Project information.", required = false)
    private List<Project> project;
    @ApiObjectField(description = "HttpStatus code returned by remote server.", required = true)
    private String httpStatusCode;
    @ApiObjectField(description = "Message returned by the remote server.", required = false)
    private String message;

    private ProjectServiceResponse(Builder builder) {
        this.success = builder.success;
        this.project = builder.project;
        this.httpStatusCode = builder.httpStatusCode;
        this.message = builder.message;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder{

        public final ProjectServiceResponse build(){
            return new ProjectServiceResponse(this);
        }

        private boolean success;
        private List<Project> project;
        private String httpStatusCode;
        private String message;

        public Builder withSuccess(Boolean input) {
            this.success = input;
            return this;
        }

        public Builder withProject(Project input) {
            if (this.project == null)
            {
                this.project = new ArrayList<>();
            }
            this.project.add(input);
            return this;
        }

        public Builder withProject(List<Project> input) {
            if (this.project == null)
            {
                this.project = new ArrayList<>();
            }
            this.project.addAll(input);
            return this;
        }

        public Builder withHttpStatusCode(String input) {
            this.httpStatusCode =input;
            return this;
        }

        public Builder withMessage(String input) {
            this.message =input;
            return this;
        }
    }
}
