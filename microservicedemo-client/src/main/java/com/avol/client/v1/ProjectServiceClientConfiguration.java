/**
 * Copyright 2000-2015 NeuStar, Inc. All rights reserved.
 * NeuStar, the Neustar logo and related names and logos are registered
 * trademarks, service marks or tradenames of NeuStar, Inc. All other
 * product names, company names, marks, logos and symbols may be trademarks
 * of their respective owners.
 */

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
