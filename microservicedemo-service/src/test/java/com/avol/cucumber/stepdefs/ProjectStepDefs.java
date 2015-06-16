package com.avol.cucumber.stepdefs;

import com.avol.api.Project;
import com.avol.api.ProjectServiceResponse;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Durga on 6/15/2015.
 */
public class ProjectStepDefs {

    private String baseURL = "http://localhost:8080/project";

    private ProjectServiceResponse response;

    @Given("^Create Project with the below data$")
    public void create_Project_with_the_below_date(DataTable dataTable) throws Throwable {
        RestTemplate restTemplate = new TestRestTemplate();
        response = restTemplate.postForObject(baseURL, getRequestObject(dataTable), ProjectServiceResponse.class, new Object[]{});
        //throw new PendingException();
    }

    @Given("^Project create success$")
    public void project_create_success() throws Throwable {
        assertEquals(HttpStatus.CREATED.toString(), response.getHttpStatusCode());
    }

    @When("^Get the created project information (.*)$")
    public void get_the_created_project_information(String projectId) throws Throwable {
        RestTemplate restTemplate = new TestRestTemplate();
        response = restTemplate.getForObject(baseURL + "/" + projectId, ProjectServiceResponse.class);
        assertEquals(HttpStatus.OK.toString(), response.getHttpStatusCode());
    }

    @Then("^Get succeeded$")
    public void get_succeeded() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(HttpStatus.OK.toString(), response.getHttpStatusCode());
    }


    private Object getRequestObject(DataTable dataTable) {
        List<Map<String, String>> projectData = dataTable.asMaps(String.class, String.class);
        Project project = new Project();
        for (Map<String, String> data : projectData) {
            project.setId(data.get("ID"));
            project.setName(data.get("NAME"));
            project.setDescription(data.get("DESCRIPTION"));
            project.setTeamSize(data.get("TEAMSIZE"));
            project.setPlatform(data.get("PLATFORM"));
        }
        return project;
    }

}
