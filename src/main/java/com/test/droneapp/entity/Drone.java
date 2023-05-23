package com.test.droneapp.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.droneapp.enums.DroneModel;
import com.test.droneapp.enums.DroneState;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "drone")
@Builder
public class Drone  extends BaseClass{

    @Size(min = 1, max = 100)
    @Column( unique = true, name = "serial_number", nullable = false)
    private String serialNumber;

    @Column(name = "battery_percentage", nullable = false, columnDefinition = "float default 100")
    @Size(max = 100)
    private double batteryPercentage;

    @Enumerated(EnumType.STRING)
    private DroneModel model;

    private double currentWeight;

    @Column(columnDefinition = "varchar(32) default 'IDLE'")
    @Enumerated(EnumType.STRING)
    private DroneState state;

    @OneToMany(mappedBy = "drone", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnore
    private Set<Medication> medicationList;
}
