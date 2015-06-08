package com.avol.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Lovababu on 6/6/2015.
 */

@Entity
@Table(name = "PROJECT")
@Setter @Getter @ToString
public class ProjectDomain {
    @Id
    @Column(name =  "ID", nullable = false)
    private String id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "TEAM_SIZE", nullable = true)
    private String teamSize;

    @Column(name = "PLATFORM", nullable = false)
    private String platform;
}
