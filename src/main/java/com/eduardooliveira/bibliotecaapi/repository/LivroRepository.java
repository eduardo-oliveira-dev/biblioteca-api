package com.eduardooliveira.bibliotecaapi.repository;

import com.eduardooliveira.bibliotecaapi.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
