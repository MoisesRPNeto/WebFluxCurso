package com.example.moisesneto.webfluxcurso.domain.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Constraint(validatedBy = {TrimStringValidation.class })//para mostrar que é para validar
@Target(FIELD) // define qual o alvo da validação
@Retention(RetentionPolicy.RUNTIME) //Validar em tempo de execução
public @interface TrimString {
    String message() default "Campo não pode conter espaços em branco no início ou no final";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
