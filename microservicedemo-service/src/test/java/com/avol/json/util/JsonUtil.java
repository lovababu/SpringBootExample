package com.avol.json.util;

import com.avol.api.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by Durga on 6/5/2015.
 */
public class JsonUtil {

    @Test
    public void jsonStringFromObject(){
        ObjectMapper objectMapper = new ObjectMapper();
        Project project = new Project();
        project.setId("XXX");
        project.setDescription("some desc.");
        project.setPlatform("Java");
        project.setTeamSize("10");
        try {
            objectMapper.writeValue(new File("./../project.json"), project);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
