package com.eduardooliveira.bibliotecaapi.controller;

import com.eduardooliveira.bibliotecaapi.dto.LivroDTO;
import com.eduardooliveira.bibliotecaapi.dto.AutorDTO;
import com.eduardooliveira.bibliotecaapi.dto.UsuarioDTO;
import com.eduardooliveira.bibliotecaapi.dto.PerfilDTO;
import com.eduardooliveira.bibliotecaapi.entity.Livro;
import com.eduardooliveira.bibliotecaapi.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<LivroDTO> listar() {
        return livroService.listarTodos().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> buscarPorId(@PathVariable Long id) {
        Optional<Livro> livro = livroService.buscarPorId(id);
        if (livro.isPresent()) {
            return ResponseEntity.ok(convertToDTO(livro.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<LivroDTO> salvar(@RequestBody Livro livro) {
        Livro livroSalvo = livroService.salvar(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(livroSalvo));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LivroDTO> atualizarParcial(@PathVariable Long id, @RequestBody Livro livroParcial) {
        try {
            Livro livroAtualizado = livroService.atualizarParcial(id, livroParcial);
            return ResponseEntity.ok(convertToDTO(livroAtualizado));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        livroService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    private LivroDTO convertToDTO(Livro livro) {
        LivroDTO dto = new LivroDTO();
        dto.setId(livro.getId());
        dto.setTitulo(livro.getTitulo());
        dto.setIsbn(livro.getIsbn());
        dto.setResumo(livro.getResumo());
        dto.setDataPublicacao(livro.getDataPublicacao());
        dto.setNumeroPaginas(livro.getNumeroPaginas());
        dto.setEditora(livro.getEditora());
        dto.setGenero(livro.getGenero());

        // Convert autores
        if (livro.getAutores() != null) {
            dto.setAutores(livro.getAutores().stream()
                    .map(autor -> {
                        AutorDTO autorDTO = new AutorDTO();
                        autorDTO.setId(autor.getId());
                        autorDTO.setNome(autor.getNome());
                        autorDTO.setNacionalidade(autor.getNacionalidade());
                        return autorDTO;
                    })
                    .collect(Collectors.toSet()));
        }

        // Convert usuario
        if (livro.getUsuario() != null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(livro.getUsuario().getId());
            usuarioDTO.setNome(livro.getUsuario().getNome());
            usuarioDTO.setEmail(livro.getUsuario().getEmail());
            
            // Convert perfil
            if (livro.getUsuario().getPerfil() != null) {
                PerfilDTO perfilDTO = new PerfilDTO();
                perfilDTO.setId(livro.getUsuario().getPerfil().getId());
                perfilDTO.setBio(livro.getUsuario().getPerfil().getBio());
                perfilDTO.setAvatarUrl(livro.getUsuario().getPerfil().getAvatarUrl());
                usuarioDTO.setPerfil(perfilDTO);
            }
            
            dto.setUsuario(usuarioDTO);
        }

        return dto;
    }
}