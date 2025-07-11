package com.eduardooliveira.bibliotecaapi.controller;

import com.eduardooliveira.bibliotecaapi.entity.Perfil;
import com.eduardooliveira.bibliotecaapi.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @GetMapping
    public List<Perfil> listar() {
        return perfilService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfil> buscarPorId(@PathVariable Long id) {
        Optional<Perfil> perfil = perfilService.buscarPorId(id);
        if (perfil.isPresent()) {
            return ResponseEntity.ok(perfil.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Perfil> salvar(@RequestBody Perfil perfil) {
        Perfil salvo = perfilService.salvar(perfil);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Perfil> atualizarParcial(@PathVariable Long id, @RequestBody Perfil perfilParcial) {
        try {
            Perfil perfilAtualizado = perfilService.atualizarParcial(id, perfilParcial);
            return ResponseEntity.ok(perfilAtualizado);
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
}