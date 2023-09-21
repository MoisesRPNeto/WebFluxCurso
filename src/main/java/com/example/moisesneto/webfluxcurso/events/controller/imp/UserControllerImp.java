package com.example.moisesneto.webfluxcurso.events.controller.imp;

import com.example.moisesneto.webfluxcurso.domain.mapper.UserMapper;
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
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<Mono<Void>> save(final UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.save(request).then()); //then pega o retorno e transforma em MONO
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> findById(String id) {
         return ResponseEntity.ok()
                 .body(userService.findById(id).map(userMapper::toResponse));
    }

    @Override
    public ResponseEntity<Flux<UserResponse>> findall() {
        return ResponseEntity.ok().body(userService.findall().map(userMapper::toResponse));
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> update(String id, UserRequest request) {
        return ResponseEntity.ok().body(
                userService.update(id, request)
                        .map(userMapper::toResponse)
        );
    }
}
