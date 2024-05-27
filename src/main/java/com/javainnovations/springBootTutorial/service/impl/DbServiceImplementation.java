package com.javainnovations.springBootTutorial.service.impl;

import com.javainnovations.springBootTutorial.model.dto.PersonDto;
import com.javainnovations.springBootTutorial.model.pojo.Person;
import com.javainnovations.springBootTutorial.repository.PersonRepo;
import com.javainnovations.springBootTutorial.service.definition.DbService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class DbServiceImplementation implements DbService {

    @Autowired
    private PersonRepo personRepo;


    @Override
    public Mono<Person> getPerson(int id) {
        Mono<PersonDto> personDtoMono = personRepo.findById(id);
        /*
        PersonDto personDto  = personDtoMono.block();
        Person p = personDto.toPerson();
        return Mono.just(p);
         */

        Mono<Person> personMono = personDtoMono.map(personDto -> {
            Person p =  personDto.toPerson();
            return p;
        });

        return personMono;

    }

    @Override
    public Mono<Boolean> savePerson(Person person) {
        PersonDto personDto = person.toPersonDto();

        Mono<PersonDto> personDtoMono = personRepo.save(personDto);

        Mono<Boolean> booleanMono = personDtoMono.map(personDtoResult -> {
            log.info("Person saved: " + personDtoResult.toString());
            return true;
        });

        return booleanMono;
    }
}
