package com.avol;

import com.avol.api.Project;
import com.avol.api.ProjectServiceResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Durga on 6/8/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProjectApplication.class)
@WebIntegrationTest("server.port:0") //spring boot start the server with random port no.
public class ProjectApplicationTest {

    private RestTemplate restTemplate = null;

    @Value("${local.server.port}")  //boot injects the port used for bootstart the application.
    int port;

    private String baseURL = null;

    @Before
    public void setUp(){
        restTemplate = new TestRestTemplate();
        baseURL = "http://localhost:"+ port + "/project";
    }

    @Test
    public void testCreate(){
        Project project = new Project();
        project.setId(random());
        project.setName("DummyProject");
        project.setDescription("dummy project desc.");
        project.setTeamSize("5");
        project.setPlatform("DummyPF");
        ProjectServiceResponse response = restTemplate.postForObject(baseURL, project, ProjectServiceResponse.class, new Object[]{null});
        //ProjectServiceResponse response = client.create(project);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED.toString(), response.getHttpStatusCode());
    }

    @Test
    public void testGet(){
        //create project.
        Project project = new Project();
        project.setId(random());
        project.setName("DummyProject");
        project.setDescription("dummy project desc.");
        project.setTeamSize("5");
        project.setPlatform("DummyPF");
        ProjectServiceResponse createResponse = restTemplate.postForObject(baseURL, project, ProjectServiceResponse.class, new Object[]{null});
        assertNotNull(createResponse);
        assertEquals(HttpStatus.CREATED.toString(), createResponse.getHttpStatusCode());

        ProjectServiceResponse response = restTemplate.getForObject(baseURL + "/" + project.getId(), ProjectServiceResponse.class, new Object[]{null});
        //ProjectServiceResponse response = client.create(project);
        assertNotNull(response);
        assertEquals(HttpStatus.OK.toString(), response.getHttpStatusCode());
        assertEquals(project.getId(), response.getProjects().get(0).getId());
    }

    @Test
    public void testList(){
        testCreate();
        ProjectServiceResponse response = restTemplate.getForObject(baseURL + "/list", ProjectServiceResponse.class, new Object[]{null});
        //ProjectServiceResponse response = client.create(project);
        assertNotNull(response);
        assertEquals(HttpStatus.OK.toString(), response.getHttpStatusCode());
        assertTrue(response.getProjects().size() > 0);
    }

    private String random(){
        Random random = new Random();
        return "PID" + random.nextInt(1000);
    }
}
