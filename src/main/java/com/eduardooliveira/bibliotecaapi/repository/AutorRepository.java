package com.eduardooliveira.bibliotecaapi.repository;

import com.eduardooliveira.bibliotecaapi.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
