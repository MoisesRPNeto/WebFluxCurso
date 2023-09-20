package com.example.moisesneto.webfluxcurso.events.controller;

import com.example.moisesneto.webfluxcurso.domain.model.request.UserRequest;
import com.example.moisesneto.webfluxcurso.domain.model.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserController {

    @PostMapping
    ResponseEntity<Mono<void>> creatUser(@RequestBody UserRequest user){
    }
    @GetMapping(value = "/{id}")
    ResponseEntity<Mono<UserResponse>> find(@PathVariable String id){
    } @GetMapping
    ResponseEntity<Flux<UserResponse>> findall(){
    }
    @PatchMapping(value = "/{id}")
    ResponseEntity<Mono<UserResponse>> creatUser(@PathVariable String id, @RequestBody UserRequest user){
    }
}
