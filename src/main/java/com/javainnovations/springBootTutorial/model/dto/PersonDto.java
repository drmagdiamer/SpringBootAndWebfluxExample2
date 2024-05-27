package com.javainnovations.springBootTutorial.model.dto;

import com.javainnovations.springBootTutorial.model.pojo.Person;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Log4j2
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("person")
public class PersonDto {
    @Id
    @Column("id")
    private int id;

    @Column("name")
    private String name;

    @Column("age")
    private int age;

    public Person toPerson() {
        return Person.builder()
                .name(this.name)
                .age(this.age)
                .hasMilitaryService(this.hasMilitaryService)
                .build();
    }

    @Column("has_military_service")
    private boolean hasMilitaryService;


}
