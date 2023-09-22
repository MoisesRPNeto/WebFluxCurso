package com.example.moisesneto.webfluxcurso.domain.service;

import com.example.moisesneto.webfluxcurso.domain.mapper.UserMapper;
import com.example.moisesneto.webfluxcurso.domain.model.User;
import com.example.moisesneto.webfluxcurso.domain.model.request.UserRequest;
import com.example.moisesneto.webfluxcurso.events.exceptions.ObjectNotFoundException;
import com.example.moisesneto.webfluxcurso.repository.mongodb.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper mapper;

    @Test
    void deve_salvar_user_sucesso() {
        final User user = getCarlos();
        final UserRequest request = getUserRequest();
        when(mapper.toEntity(request)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(Mono.just(user));

        Mono<User> resultado = userService.save(request);

        StepVerifier.create(resultado)  //deve ser criado como publish
                .expectNextMatches(Objects::nonNull) //verificar se não é nulo
                .expectComplete() //esperado ser completado
                .verify(); //verificar
        Mockito.verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void findById_teste() {
        final User user = getCarlos();
        when(userRepository.findById(user.getId())).thenReturn(Mono.just(user));

        Mono<User> resultado = userService.findById(user.getId());

        StepVerifier.create(resultado)  //deve ser criado como publish
                .expectNextMatches(result -> result.getClass() == User.class
                        && result.getId() == user.getId())//verificar classe e id
                .expectComplete() //esperado ser completado
                .verify(); //verificar

        Mockito.verify(userRepository, times(1)).findById(anyString());
    }

    @Test
    void findall_teste() {
        final User user = getCarlos();
        when(userRepository.findall()).thenReturn(Flux.just(user));

        Flux<User> resultado = userService.findall();

        StepVerifier.create(resultado)  //deve ser criado como publish
                .expectNextMatches(result -> result.getClass() == User.class)//verificar classe e id
                .expectComplete() //esperado ser completado
                .verify(); //verificar

        Mockito.verify(userRepository, times(1)).findall();
    }

    @Test
    void update_test() {
        final UserRequest request = getUserRequest();
        final User user = getCarlos();
        when(mapper.requestToEntity(request,user)).thenReturn(user);
        when(userRepository.findById(user.getId())).thenReturn(Mono.just(user));
        when(userRepository.save(user)).thenReturn(Mono.just(user));

        Mono<User> resultado = userService.update(user.getId(), request);

        StepVerifier.create(resultado)  //deve ser criado como publish
                .expectNextMatches(result -> result.getClass() == User.class
                        && result.getId() == user.getId())//verificar classe e id
                .expectComplete() //esperado ser completado
                .verify(); //verificar

        Mockito.verify(userRepository, times(1)).findById(anyString());
        Mockito.verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void delete_teste() {
        final User user = getCarlos();
        when(userRepository.findAndRemover(user.getId())).thenReturn(Mono.just(user));

        Mono<User> resultado = userService.delete(user.getId());

        StepVerifier.create(resultado)  //deve ser criado como publish
                .expectNextMatches(result -> result.getClass() == User.class)//verificar classe e id
                .expectComplete() //esperado ser completado
                .verify(); //verificar

        Mockito.verify(userRepository, times(1)).findAndRemover(anyString());
    }

    @Test
    void handleNotFound_teste() {
        when(userRepository.findById("123")).thenReturn(Mono.empty());

        assertThrows(ObjectNotFoundException.class, () -> userService.findById("123"));
    }


    private static User getCarlos() {
        return User.builder()
                .id("1234")
                .nome("Carlos")
                .email("carlos@gmail.com")
                .senha("123")
                .build();
    }
    private static UserRequest getUserRequest() {
        return UserRequest.builder()
                .nome("Carlos")
                .email("carlos@gmail.com")
                .senha("123")
                .build();
    }
}