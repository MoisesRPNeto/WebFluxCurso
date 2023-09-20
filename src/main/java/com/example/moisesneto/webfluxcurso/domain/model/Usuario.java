package com.example.moisesneto.webfluxcurso.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Usuario {

    @Id
    private String id;
    private String nome;

    @Indexed(unique = true)
    private String email;
    private String  senha;

}
