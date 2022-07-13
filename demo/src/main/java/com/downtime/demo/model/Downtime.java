package com.downtime.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dms")

public class Downtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "provider_name", unique = true)
    private String provider;

    @Column(name = "flow_name")
    private String flow;

    //CR: Please use variable names in camelCase and continue snake case in DB table names
    @Column(name = "down_from")
    private LocalDateTime down_from;

    @Column(name = "down_to")
    private LocalDateTime down_to;

}