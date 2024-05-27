package com.javainnovations.springBootTutorial.controller;


import com.javainnovations.springBootTutorial.model.pojo.Person;

import com.javainnovations.springBootTutorial.service.definition.DbService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import reactor.core.publisher.Mono;

@RestController
@Log4j2
public class MainController {

    @Autowired
    private DbService dbService;

    //DO NOT DO THAT
//    int id;

    @GetMapping("/")
    public String root(){
        log.info("Hello from root");
        return "HI";
    }

    @PostMapping("/")
    public String root2(){
        log.info("Hello from root2");
        return "Hello From Post";
    }

   @GetMapping(value="/savePersonFromGetQueryParam")
    public Mono<String> savePersonFromGetQueryParam(@RequestParam  String name,
                                           @RequestParam int age,
                                           @RequestParam boolean hasMilitaryService){
        log.info("Hello from savePersonFromForm");
        log.info("Name: " + name);
        log.info("Age: " + age);
        log.info("Has Military Service: " + hasMilitaryService);
        Mono<Boolean> booleanMono = dbService.savePerson(new Person(name, age, hasMilitaryService));
        Mono<String> stringMono = booleanMono.map(result -> {
            if(result == true){
                return "Person Saved";
            } else {
                return "Person Not Saved";
            }
        });
        return stringMono;
    }

    @GetMapping(value="/savePersonFromGetForm")
    public Mono<String> savePersonFromForm(@ModelAttribute Person person){
        Mono<Boolean> booleanMono = dbService.savePerson(person);
        Mono<String> stringMono = booleanMono.map(result -> {
            if(result == true){
                return "Person Saved";
            } else {
                return "Person Not Saved";
            }
        });
        return stringMono;
    }

    //This is no longer supported in WebFlux. @RequestParam in webflux only supports query parameters
//    @PostMapping(value="/savePersonFromPostForm", consumes = "application/x-www-form-urlencoded")
//    public Mono<String> savePersonFromPostForm( String name,
//                                               @RequestParam(value="age", required=false)  String age,
//                                               @RequestParam(value="hasMilitaryService", required=false)  String hasMilitaryService){
//        log.info("Hello from savePersonFromPostForm");
//        log.info("Name: " + name);
//        log.info("Age: " + age);
//        log.info("Has Military Service: " + hasMilitaryService);
////        dbService.savePerson(new Person(name, age, hasMilitaryService)).subscribe();
//        return Mono.just("Hello from savePersonFromPostForm");
//
//    }

    //To do a form post, use the following approach
    @PostMapping("/savePersonFromPostForm")
    public String savePersonFromPostForm(@ModelAttribute Person person){
        log.info("Hello from savePersonUsingBody");
        log.info("Name: " + person.getName());
        log.info("Age: " + person.getAge());
        log.info("Has Military Service: " + person.isHasMilitaryService());
        dbService.savePerson(person).subscribe();
        return "Hello from savePersonUsingBody";
    }


    @PostMapping("/savePersonUsingBody")
    public String savePersonUsingBody(@RequestBody(required = false) Person person){
        log.info("Hello from savePersonUsingBody");
        log.info("Name: " + person.getName());
        log.info("Age: " + person.getAge());
        log.info("Has Military Service: " + person.isHasMilitaryService());
        dbService.savePerson(person).subscribe();
        return "Hello from savePersonUsingBody";
    }

    @GetMapping("/savePersonFromPathParam/{name}/{age}/{hasMilitaryService}")
    public String savePersonFromPathParam(@PathVariable("name") String name,
                                          @PathVariable("age") int age,
                                          @PathVariable("hasMilitaryService") boolean hasMilitaryService){
        log.info("Hello from savePersonFromPathParam");
        log.info("Name: " + name);
        log.info("Age: " + age);
        log.info("Has Military Service: " + hasMilitaryService);
        dbService.savePerson(new Person(name, age, hasMilitaryService)).subscribe();
        return "Hello from savePersonFromPathParam";
    }

    @GetMapping("/savePersonFromHeaderParam")
    public String savePersonFromHeaderParam(@RequestHeader(value = "name") String name,
                                            @RequestHeader(value = "age") int age,
                                            @RequestHeader(value = "hasMilitaryService") boolean hasMilitaryService){
        log.info("Hello from savePersonFromHeaderParam");
        log.info("Name: " + name);
        log.info("Age: " + age);
        log.info("Has Military Service: " + hasMilitaryService);
        dbService.savePerson(new Person(name, age, hasMilitaryService)).subscribe();
        return "Hello from savePersonFromHeaderParam";
    }

    @GetMapping("/getPerson/{id}")
    public Mono<Person> getPerson(@PathVariable("id") String id){
        log.info("Hello from getPerson");
        log.info("id: " + id);

        return dbService.getPerson(Integer.parseInt(id));
    }



    @GetMapping("/test")
    public String test(){
        log.info("test");
        return (3/0)+ " is bad";
    }

    @GetMapping("/test2")
    public String test2(){
        Person p = null;
        return p.toString();
    }

    @ExceptionHandler({ ArithmeticException .class })
    public ResponseEntity<String> handleArithmeticException (final ArithmeticException  ex) {

        String bodyOfResponse = ex.getMessage();
        log.info("Testing ArithmeticException Handler {}", bodyOfResponse);
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ NullPointerException.class })
    public ResponseEntity<String> handleException(final NullPointerException ex) {
        String bodyOfResponse = ex.getMessage();
        log.info("Testing Exception Handler {}", bodyOfResponse);
        log.info("Testing Exception Handler {}", ex.toString());
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException ex) {
        String bodyOfResponse = ex.getMessage();
        log.info("handleMethodArgumentTypeMismatchException {}", bodyOfResponse);
        log.info("handleMethodArgumentTypeMismatchException {}", ex.toString());
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.BAD_REQUEST);
    }



}
