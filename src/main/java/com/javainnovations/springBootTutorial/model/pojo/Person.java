package com.javainnovations.springBootTutorial.model.pojo;

import com.javainnovations.springBootTutorial.model.dto.PersonDto;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Log4j2
@Builder
@AllArgsConstructor
public class Person {
    private String name;
    private int age;
    private boolean hasMilitaryService;
    private List<Car> car;

    public Person() {
        this.car = new ArrayList<>();
    }

    public Person(String name, int age, boolean hasMilitaryService) {
        this();
        this.name = name;
        this.age = age;
        this.hasMilitaryService = hasMilitaryService;
    }

    public void addCar(Car car){
        this.car.add(car);
    }

    public PersonDto toPersonDto() {
        return PersonDto.builder()
                .name(this.name)
                .age(this.age)
                .hasMilitaryService(this.hasMilitaryService)
                .build();
    }


}


