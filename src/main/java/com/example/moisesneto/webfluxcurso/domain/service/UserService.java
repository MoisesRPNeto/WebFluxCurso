package com.example.moisesneto.webfluxcurso.domain.service;

import com.example.moisesneto.webfluxcurso.domain.mapper.UserMapper;
import com.example.moisesneto.webfluxcurso.domain.model.User;
import com.example.moisesneto.webfluxcurso.domain.model.request.UserRequest;
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

    public Mono<User> save(final UserRequest user){
        return userRepository.save(userMapper.toEntity(user));
    }

    public Flux<User> findall(){
        return userRepository.findall();
    }
}
