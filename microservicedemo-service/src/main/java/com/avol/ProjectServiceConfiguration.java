/**
 * Copyright 2012-2015 NeuStar, Inc. All rights reserved.
 * NeuStar, the Neustar logo and related names and logos are registered
 * trademarks, service marks or tradenames of NeuStar, Inc. All other
 * product names, company names, marks, logos and symbols may be trademarks
 * of their respective owners.
 */

package com.avol;

import org.springframework.context.annotation.Configuration;

/**
 * Configuration for service microservicedemo.
 * Register service specific beans here.
 */

@Configuration
public class ProjectServiceConfiguration {


    /*private SpringSwaggerConfig springSwaggerConfig;

    @Autowired
    public void setSpringSwaggerConfig( SpringSwaggerConfig springSwaggerConfig )
    {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean //Don't forget the @Bean annotation
    public SwaggerSpringMvcPlugin customImplementation()
    {
        return new SwaggerSpringMvcPlugin( this.springSwaggerConfig )
                .apiInfo( apiInfo() )
                .includePatterns( "/api-docs.*" );
    }

    private ApiInfo apiInfo()
    {
        ApiInfo apiInfo = new ApiInfo(
                "Microservices Demo",
                "Application designed using Spirng boot. \n and Swagger for API documentation.",
                "My Apps API terms of service",
                "durga.padala@tpgsi.com",
                "Free License.",
                "..."
        );
        return apiInfo;
    }*/
}
