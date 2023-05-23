package com.test.droneapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medication")
@Builder
public class Medication extends BaseClass{

    @Pattern(regexp="^[a-zA-Z0-9_-]$",message="Should contain only letters, numbers, ‘-‘ and ‘_")
    private String name;

    @Pattern(regexp="^[A-Z0-9_]$",message="Should contain only upper case, numbers and ‘_")
    private String code;

    private double weight;

    private String image;

    @ManyToOne
    @JoinColumn(name = "drone_id")
    @JsonIgnore
    private Drone drone;
}
