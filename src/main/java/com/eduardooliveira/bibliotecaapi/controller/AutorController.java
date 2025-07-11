package com.eduardooliveira.bibliotecaapi.controller;

import com.eduardooliveira.bibliotecaapi.dto.AutorDTO;
import com.eduardooliveira.bibliotecaapi.entity.Autor;
import com.eduardooliveira.bibliotecaapi.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping
    public ResponseEntity<AutorDTO> salvar(@RequestBody Autor autor) {
        Autor salvo = autorService.salvar(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(salvo));
    }

    @GetMapping
    public List<AutorDTO> listar() {
        return autorService.listarTodos().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> buscarPorId(@PathVariable Long id) {
        Optional<Autor> autor = autorService.buscarPorId(id);
        if (autor.isPresent()) {
            return ResponseEntity.ok(convertToDTO(autor.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AutorDTO> atualizarParcial(@PathVariable Long id, @RequestBody Autor autorParcial) {
        try {
            Autor autorAtualizado = autorService.atualizarParcial(id, autorParcial);
            return ResponseEntity.ok(convertToDTO(autorAtualizado));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            autorService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private AutorDTO convertToDTO(Autor autor) {
        AutorDTO dto = new AutorDTO();
        dto.setId(autor.getId());
        dto.setNome(autor.getNome());
        dto.setNacionalidade(autor.getNacionalidade());
        return dto;
    }
}