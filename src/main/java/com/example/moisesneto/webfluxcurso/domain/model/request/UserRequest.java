package com.example.moisesneto.webfluxcurso.domain.model.request;

import com.example.moisesneto.webfluxcurso.domain.validation.TrimString;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    @TrimString //anotação criada no projeto, valida espaços vazios
    @NotBlank(message = "NOME não pode ser vazio ou nulo")//não pode ser vazio ou nulo JakartaValidation
    @Size(min = 3, max = 50, message = "mínimo de 3 e máximo de 50 caracteres") //defini a quantidade de caracteres
    private String nome;

    @TrimString //anotação criada no projeto, valida espaços vazios
    @Email
    @NotBlank(message = "EMAIL não pode ser vazio ou nulo")//não pode ser vazio ou nulo JakartaValidation
    private String email;

    @TrimString //anotação criada no projeto, valida espaços vazios
    @NotBlank(message = "SENHA não pode ser vazio ou nulo")//não pode ser vazio ou nulo JakartaValidation
    @Size(min = 3, max = 50, message = "mínimo de 3 e máximo de 50 caracteres") //defini a quantidade de caracteres
    private String senha;

    //Só valida se for adicionado o @Valid como parâmetro na requisição
}
