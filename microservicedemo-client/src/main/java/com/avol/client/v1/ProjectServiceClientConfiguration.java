package com.avol.client.v1;

import lombok.Getter;
import lombok.Setter;

public class ProjectServiceClientConfiguration {

    @Setter @Getter
    private String baseUrl;

    @Setter @Getter
    private String connectionTimeOut;

    @Setter @Getter
    private String timeOut;

    @Setter @Getter
    private String retries;
}
