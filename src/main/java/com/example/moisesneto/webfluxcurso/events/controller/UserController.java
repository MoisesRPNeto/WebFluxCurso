package com.example.moisesneto.webfluxcurso.events.controller;

import com.example.moisesneto.webfluxcurso.domain.model.request.UserRequest;
import com.example.moisesneto.webfluxcurso.domain.model.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserController {
    @PostMapping
    ResponseEntity<Mono<Void>> save(@Valid @RequestBody UserRequest user);

    @GetMapping(value = "/{id}")
    ResponseEntity<Mono<UserResponse>> findById(@PathVariable String id);

    @GetMapping
    ResponseEntity<Flux<UserResponse>> findall();

    @PatchMapping(value = "/{id}")
    ResponseEntity<Mono<UserResponse>> update(@PathVariable String id, @RequestBody UserRequest user);
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Mono<Void>> update(@PathVariable String id);
}
