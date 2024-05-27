package com.javainnovations.springBootTutorial.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@Log4j2
@Builder
@AllArgsConstructor
public class Car {
//    static int sharedCounter;
    private String brand;
    private String model;
    private int year;
    private String color;
    private Person person;
}


