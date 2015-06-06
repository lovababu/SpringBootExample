/**
 * Copyright 2000-2015 NeuStar, Inc. All rights reserved.
 * NeuStar, the Neustar logo and related names and logos are registered
 * trademarks, service marks or tradenames of NeuStar, Inc. All other
 * product names, company names, marks, logos and symbols may be trademarks
 * of their respective owners.
 */

package com.avol.client.v1;

import com.avol.api.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.Dispatcher;
import feign.Feign;
import feign.Headers;
import feign.Param;
import feign.Request;
import feign.RequestInterceptor;
import feign.RequestLine;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

;


public class ProjectServiceClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceClient.class);

    private final ObjectMapper mapper;
    private final ProjectServiceClientConfiguration conf;

    public interface ProjectService {

        @Headers({ "Content-type: application/json" })
        @RequestLine("POST /project")
        String create(Project project);

        @RequestLine("GET /project/{id}")
        Project get(@Param("id") String id);

        @RequestLine("PUT /project/{id}")
        String update(@Param("id") String id, Project project);

        @RequestLine("DELETE /project/{id}")
        String delete(@Param("id") String id);
    }

    public ProjectServiceClient(ProjectServiceClientConfiguration config) {
        //super(config);
        this.mapper = new ObjectMapper();
        this.conf = config;
    }

    public ProjectService build() {
        return this.build(ProjectService.class);
    }

    private <I> I build(Class<I> clazz) {
        return feignBuilder()
                .target(clazz, conf.getBaseUrl());
    }

    private Feign.Builder feignBuilder() {
        List<RequestInterceptor> interceptors = new ArrayList<>();

        com.squareup.okhttp.OkHttpClient okHttpClient = getHttpClient();

        final ErrorDecoder decoder = new ErrorDecoder.Default();

        return Feign.builder()
                .client(new feign.okhttp.OkHttpClient(okHttpClient))
                .requestInterceptors(interceptors)
                .encoder(new JacksonEncoder(mapper))
                //.decoder(new JacksonDecoder(mapper))
                .errorDecoder(decoder)
                .logger(new Slf4jLogger())
                .options(new Request.Options(
                        Integer.parseInt(conf.getConnectionTimeOut()),
                        Integer.parseInt(conf.getTimeOut())))
                        // already set, but just for documentation purposes
                .retryer(new Retryer.Default(100, SECONDS.toMillis(1), Integer.parseInt(conf.getRetries())));
    }

    protected com.squareup.okhttp.OkHttpClient getHttpClient() {
        // okhttp customized dispatcher & client
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(100);
        dispatcher.setMaxRequestsPerHost(100);

        com.squareup.okhttp.OkHttpClient okHttpClient = new com.squareup.okhttp.OkHttpClient();
        okHttpClient.setDispatcher(dispatcher);
        return okHttpClient;
    }
}
