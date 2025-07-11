package com.eduardooliveira.bibliotecaapi.repository;

import com.eduardooliveira.bibliotecaapi.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
