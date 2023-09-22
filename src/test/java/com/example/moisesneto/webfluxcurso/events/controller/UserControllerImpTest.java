package com.example.moisesneto.webfluxcurso.events.controller;

import com.example.moisesneto.webfluxcurso.domain.mapper.UserMapper;
import com.example.moisesneto.webfluxcurso.domain.model.User;
import com.example.moisesneto.webfluxcurso.domain.model.request.UserRequest;
import com.example.moisesneto.webfluxcurso.domain.service.UserService;
import com.mongodb.reactivestreams.client.MongoClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWebTestClient
class UserControllerImpTest {

    @Autowired //Injetando para testar os endpoints
    private WebTestClient webTestClient;

    @MockBean //Mockando Classe importada
    private UserService service;
    @MockBean //Mockando Classe importada
    private UserMapper mapper;

    @MockBean //Mockando MongoDB
    private MongoClient mongoClient;
    @Test
    @DisplayName("chamado integração no endpoint para salvar")
    void save() {
        UserRequest request = UserRequest.builder()
                .nome("Moises")
                .email("moises@gmail.com")
                .senha("123")
                .build();

        when(service.save(any(UserRequest.class))).thenReturn(Mono.just(User.builder().build()));

        webTestClient.post().uri("/users") //Post no endpoint
                .contentType(MediaType.APPLICATION_JSON) //evento em json
                .body(BodyInserters.fromValue(request)) //recebendo UserRequest no body
                .exchange() //possibilitar pegar o retorno
                .expectStatus().isCreated(); //expectativa de retornar um created

        verify(service, times(1)).save(any(UserRequest.class));
    }

    @Test
    void findById() {
    }

    @Test
    void findall() {
    }

    @Test
    void update() {
    }

    @Test
    void testUpdate() {
    }
}