package com.javainnovations.springBootTutorial.repository;

import com.javainnovations.springBootTutorial.model.dto.PersonDto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PersonRepo extends ReactiveCrudRepository<PersonDto, Integer> {
}