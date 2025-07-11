package com.eduardooliveira.bibliotecaapi.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private PerfilDTO perfil;
}