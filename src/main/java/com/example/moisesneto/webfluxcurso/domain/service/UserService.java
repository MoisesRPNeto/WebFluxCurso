package com.example.moisesneto.webfluxcurso.domain.service;

import com.example.moisesneto.webfluxcurso.domain.mapper.UserMapper;
import com.example.moisesneto.webfluxcurso.domain.model.User;
import com.example.moisesneto.webfluxcurso.domain.model.request.UserRequest;
import com.example.moisesneto.webfluxcurso.events.exceptions.ObjectNotFoundException;
import com.example.moisesneto.webfluxcurso.repository.mongodb.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Mono<User> save(final UserRequest user) {
        return userRepository.save(userMapper.toEntity(user));
    }

    public Mono<User> findById(final String id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new ObjectNotFoundException(
                        String.format("Objeto não encontrado. Id: %s, Type: %s", id, User.class.getSimpleName())
                )));
    }

    public Flux<User> findall() {
        return userRepository.findall();
    }

    public Mono<User> update(final String id, final UserRequest userRequest) {
        return findById(id)
                .map(entity -> userMapper.requestToEntity(userRequest, entity))
                .flatMap(userRepository::save);
    }

}
