package com.downtime.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

// CR appreciate getters setters
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BodyData {

    //@Column(name = "down_from")
    private LocalDateTime down_from;

    //@Column(name = "down_to")
    private LocalDateTime down_to;

}