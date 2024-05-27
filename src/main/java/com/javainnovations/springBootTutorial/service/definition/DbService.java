package com.javainnovations.springBootTutorial.service.definition;

import com.javainnovations.springBootTutorial.model.pojo.Person;
import reactor.core.publisher.Mono;

public interface DbService {
    Mono<Person> getPerson(int id);
    Mono<Boolean>   savePerson(Person person);
}
