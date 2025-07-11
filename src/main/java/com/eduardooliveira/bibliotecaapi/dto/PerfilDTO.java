package com.eduardooliveira.bibliotecaapi.dto;

import lombok.Data;

@Data
public class PerfilDTO {
    private Long id;
    private String bio;
    private String avatarUrl;
}