package com.example.moisesneto.webfluxcurso.domain.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {

    private String nome;
    private String email;
    private String  senha;
}