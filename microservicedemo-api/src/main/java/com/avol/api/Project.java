package com.avol.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

/**
 * Created by Durga on 6/5/2015.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiObject(show = true, description = "Project json structure.")
public class Project {
    @ApiObjectField(description = "specify the project id.", required = true)
    private String id;
    @ApiObjectField(description = "specify the project name.", required = true)
    private String name;
    @ApiObjectField(description = "specify the description about project.", required = false)
    private String description;
    @ApiObjectField(description = "specify the size of the team.", required = false)
    private String teamSize;
    @ApiObjectField(description = "specify the platform on which the project going to be build.", required = false)
    private String platform;
}
