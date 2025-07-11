package com.eduardooliveira.bibliotecaapi.controller;

import com.eduardooliveira.bibliotecaapi.dto.UsuarioDTO;
import com.eduardooliveira.bibliotecaapi.dto.PerfilDTO;
import com.eduardooliveira.bibliotecaapi.entity.Usuario;
import com.eduardooliveira.bibliotecaapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody Usuario usuario) {
        Usuario salvo = usuarioService.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(salvo));
    }

    @GetMapping
    public List<UsuarioDTO> listar() {
        return usuarioService.listarTodos().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(convertToDTO(usuario.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizarParcial(@PathVariable Long id, @RequestBody Usuario usuarioParcial) {
        try {
            Usuario usuarioAtualizado = usuarioService.atualizarParcial(id, usuarioParcial);
            return ResponseEntity.ok(convertToDTO(usuarioAtualizado));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            usuarioService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        
        // Convert perfil
        if (usuario.getPerfil() != null) {
            PerfilDTO perfilDTO = new PerfilDTO();
            perfilDTO.setId(usuario.getPerfil().getId());
            perfilDTO.setBio(usuario.getPerfil().getBio());
            perfilDTO.setAvatarUrl(usuario.getPerfil().getAvatarUrl());
            dto.setPerfil(perfilDTO);
        }
        
        return dto;
    }
}