package com.example.moisesneto.webfluxcurso.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data  //Getter, Setter, AllArgsConstructor
@Builder  //Possibilita a construção rápida dos objetos
@Document //Entende que é um documento do mongo para conexão
public class User {

    @Id //indica qual o atributo id e possibilita sua geração randomica
    private String id;
    private String nome;

    @Indexed(unique = true) //não permite dois iguais
    private String email;
    private String  senha;

}
