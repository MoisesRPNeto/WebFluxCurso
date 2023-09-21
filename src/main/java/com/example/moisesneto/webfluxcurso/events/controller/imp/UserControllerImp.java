package com.example.moisesneto.webfluxcurso.events.controller.imp;

import com.example.moisesneto.webfluxcurso.domain.model.request.UserRequest;
import com.example.moisesneto.webfluxcurso.domain.model.response.UserResponse;
import com.example.moisesneto.webfluxcurso.domain.service.UserService;
import com.example.moisesneto.webfluxcurso.events.controller.UserController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserControllerImp implements UserController {

    private final UserService userService;

    @Override
    public ResponseEntity<Mono<Void>> save(final UserRequest user) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.save(user).then()); //then pega o retorno e transforma em MONO
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> find(String id) {
        return null;
    }

    @Override
    public ResponseEntity<Flux<UserResponse>> findall() {
        return ResponseEntity.ok().body(userService.findall().cast(UserResponse.class));
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> save(String id, UserRequest user) {
        return null;
    }
}
