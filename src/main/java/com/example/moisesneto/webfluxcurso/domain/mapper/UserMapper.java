package com.example.moisesneto.webfluxcurso.domain.mapper;

import com.example.moisesneto.webfluxcurso.domain.model.User;
import com.example.moisesneto.webfluxcurso.domain.model.request.UserRequest;
import com.example.moisesneto.webfluxcurso.domain.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring", //indica que o espring vai gerenciar
        nullValuePropertyMappingStrategy = IGNORE, //Ignora o mapeamento se o valor for nulo
        nullValueCheckStrategy = ALWAYS //verifica se todos os atributos são nulos
        )
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(final UserRequest userRequest);

    @Mapping(target = "id", ignore = true)
    User requestToEntity(final UserRequest userRequest, @MappingTarget final User user); //@MappingTarget define o alvo pra quem vai mapear

    UserResponse toResponse(final User user);
}
