package com.avol.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Lovababu on 6/6/2015.
 */

@Entity
public class ProjectDomain {

    @Id
    private String id;

    private String name;

    private String description;

    private String teamSize;

    private String platForm;
}
