package com.aogdeveloper.banconova.application.mappers;

public interface DTOMapper<ENTITY, DTO> {
    DTO toDTO(ENTITY entity);
    ENTITY toEntity(DTO dto);
}
