package com.test.droneapp.dtos.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicationPayload {
    private String name;
    private String code;
    private double weight;
    private String image;
}
