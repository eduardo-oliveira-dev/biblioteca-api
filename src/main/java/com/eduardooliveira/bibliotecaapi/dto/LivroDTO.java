package com.eduardooliveira.bibliotecaapi.dto;

import com.eduardooliveira.bibliotecaapi.entity.GeneroLivro;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class LivroDTO {
    private Long id;
    private String titulo;
    private String isbn;
    private String resumo;
    private Set<AutorDTO> autores;
    private LocalDate dataPublicacao;
    private Integer numeroPaginas;
    private String editora;
    private GeneroLivro genero;
    private UsuarioDTO usuario;
}