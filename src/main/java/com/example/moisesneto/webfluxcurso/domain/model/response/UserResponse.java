package com.example.moisesneto.webfluxcurso.domain.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private String id;
    private String nome;
    private String email;
    private String  senha;

}
