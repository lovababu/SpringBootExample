package com.avol;

import com.avol.api.Project;
import com.avol.client.v1.ProjectServiceClient;
import com.avol.client.v1.ProjectServiceClientConfiguration;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

/**
 * Created by Durga on 6/5/2015.
 */
public class ProjectServiceE2ETest {

    //Note: Make Sure your service is running prior to run these test cases.

    private ProjectServiceClient.ProjectService client;

    @Before
    public void setUp(){
        ProjectServiceClientConfiguration clientConfiguration = new ProjectServiceClientConfiguration();
        clientConfiguration.setBaseUrl("http://localhost:8080");
        clientConfiguration.setConnectionTimeOut("60");
        clientConfiguration.setRetries("2");
        clientConfiguration.setTimeOut("100");
        client = new ProjectServiceClient(clientConfiguration).build();
    }

    //@Test
    public void testCreate(){
        Project project = new Project();
        String response = client.create(project);
        assertEquals("Request processed Successfully.", response);
    }
}
