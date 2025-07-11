package com.eduardooliveira.bibliotecaapi.controller;

import com.eduardooliveira.bibliotecaapi.dto.PerfilDTO;
import com.eduardooliveira.bibliotecaapi.entity.Perfil;
import com.eduardooliveira.bibliotecaapi.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @GetMapping
    public List<PerfilDTO> listar() {
        return perfilService.listarTodos().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilDTO> buscarPorId(@PathVariable Long id) {
        Optional<Perfil> perfil = perfilService.buscarPorId(id);
        if (perfil.isPresent()) {
            return ResponseEntity.ok(convertToDTO(perfil.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PerfilDTO> salvar(@RequestBody Perfil perfil) {
        Perfil salvo = perfilService.salvar(perfil);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(salvo));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PerfilDTO> atualizarParcial(@PathVariable Long id, @RequestBody Perfil perfilParcial) {
        try {
            Perfil perfilAtualizado = perfilService.atualizarParcial(id, perfilParcial);
            return ResponseEntity.ok(convertToDTO(perfilAtualizado));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            perfilService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private PerfilDTO convertToDTO(Perfil perfil) {
        PerfilDTO dto = new PerfilDTO();
        dto.setId(perfil.getId());
        dto.setBio(perfil.getBio());
        dto.setAvatarUrl(perfil.getAvatarUrl());
        return dto;
    }
}