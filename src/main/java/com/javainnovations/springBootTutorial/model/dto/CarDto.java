package com.javainnovations.springBootTutorial.model.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@Log4j2
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    private int id;
    private String brand;
    private String model;
    private int year;
    private String color;
    private int personId;
}
