package com.example.moisesneto.webfluxcurso.domain.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class TrimStringValidation implements ConstraintValidator<TrimString, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(value) || value.trim().length() == value.length(); //Valida objeto nulo e se tem espaços vazios
    }
}
