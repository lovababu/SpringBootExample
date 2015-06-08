package com.avol.client.v1;

import com.avol.api.Project;
import com.avol.api.ProjectServiceResponse;
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
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by Durga on 6/8/2015.
 *
 * Client application used to communicate with service.
 */

public class ProjectServiceClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceClient.class);

    private final ObjectMapper mapper;
    private final ProjectServiceClientConfiguration conf;

    public interface ProjectService {

        @Headers({ "Content-type: application/json" })
        @RequestLine("POST /project")
        ProjectServiceResponse create(Project project);

        @Headers({ "Content-type: application/json" })
        @RequestLine("GET /project/{id}")
        ProjectServiceResponse get(@Param("id") String id);

        @Headers({ "Content-type: application/json" })
        @RequestLine("PUT /project/{id}")
        ProjectServiceResponse update(@Param("id") String id, Project project);

        @Headers({ "Content-type: application/json" })
        @RequestLine("DELETE /project/{id}")
        ProjectServiceResponse delete(@Param("id") String id);

        @Headers({ "Content-type: application/json" })
        @RequestLine("GET /list")
        ProjectServiceResponse list();
    }

    public ProjectServiceClient(ProjectServiceClientConfiguration config) {
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
                .decoder(new JacksonDecoder(mapper))
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
