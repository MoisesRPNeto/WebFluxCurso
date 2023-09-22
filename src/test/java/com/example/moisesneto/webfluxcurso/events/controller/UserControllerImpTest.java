package com.example.moisesneto.webfluxcurso.events.controller;

import com.example.moisesneto.webfluxcurso.domain.mapper.UserMapper;
import com.example.moisesneto.webfluxcurso.domain.model.User;
import com.example.moisesneto.webfluxcurso.domain.model.request.UserRequest;
import com.example.moisesneto.webfluxcurso.domain.model.response.UserResponse;
import com.example.moisesneto.webfluxcurso.domain.service.UserService;
import com.mongodb.reactivestreams.client.MongoClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;

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
                .contentType(APPLICATION_JSON) //evento em json
                .body(BodyInserters.fromValue(request)) //recebendo UserRequest no body
                .exchange() //possibilitar pegar o retorno
                .expectStatus().isCreated(); //expectativa de retornar um created

        verify(service, times(1)).save(any(UserRequest.class));
    }
    @Test
    @DisplayName("chamado integração no endpoint para BADREQUEST") //TODO testar demais cenários
    void noSave() {
        UserRequest request = UserRequest.builder()
                .nome(" Moises")
                .email("moises@gmail.com")
                .senha("123")
                .build();


        webTestClient.post().uri("/users") //Post no endpoint
                .contentType(APPLICATION_JSON) //evento em json
                .body(BodyInserters.fromValue(request)) //recebendo UserRequest no body
                .exchange() //possibilitar pegar o retorno
                .expectStatus().isBadRequest() //expectativa de retornar um BadRequest
                .expectBody() //Pegar valores do body
                .jsonPath("$.path").isEqualTo("/users") //comparar valores de retorno
                .jsonPath("$.status").isEqualTo(BAD_REQUEST.value())//comparar valores de retorno
                .jsonPath("$.error").isEqualTo("Validation Error")//comparar valores de retorno
                .jsonPath("$.message").isEqualTo("Erro na Validação dos atributos")//comparar valores de retorno
                .jsonPath("$.errors[0].fieldName").isEqualTo("name")//comparar valores de retorno
                .jsonPath("$.errors[0].message").isEqualTo("Não sei qual o erro, dedvo testar e adicionar aqui");//comparar valores de retorno

    }

    @Test
    void findById() {
        String id = "123";
        UserResponse response = UserResponse.builder()
                .id(id)
                .nome("Moises")
                .email("moises@gmail.com")
                .senha("123")
                .build();
        User user = User.builder()
                .id(id)
                .nome("Moises")
                .email("moises@gmail.com")
                .senha("123")
                .build();
        when(service.findById(anyString())).thenReturn(Mono.just(user));
        when(mapper.toResponse(any(User.class))).thenReturn(response);

        webTestClient.get().uri("/users/" + id) //Get no endpoint
                .accept(APPLICATION_JSON) //evento em json
                .exchange() //possibilitar pegar o retorno
                .expectStatus().isOk()//expectativa de retornar um OK
                .expectBody()
                .jsonPath("$.id").isEqualTo(id) //comparar valores de retorno
                .jsonPath("$.nome").isEqualTo(response.getNome())//comparar valores de retorno
                .jsonPath("$.email").isEqualTo(response.getEmail())//comparar valores de retorno
                .jsonPath("$.senha").isEqualTo(response.getSenha());//comparar valores de retorno



        verify(service, times(1)).save(any(UserRequest.class));

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