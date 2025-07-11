package com.eduardooliveira.bibliotecaapi.service;

import com.eduardooliveira.bibliotecaapi.entity.Usuario;
import com.eduardooliveira.bibliotecaapi.entity.Perfil;
import com.eduardooliveira.bibliotecaapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario) {
        Perfil perfil = usuario.getPerfil();
        if (perfil != null) {
            perfil.setUsuario(usuario);
        }
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario atualizarParcial(Long id, Usuario dadosParciais) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));

        if (dadosParciais.getNome() != null) {
            usuario.setNome(dadosParciais.getNome());
        }
        if (dadosParciais.getEmail() != null) {
            usuario.setEmail(dadosParciais.getEmail());
        }

        // Atualizar perfil parcialmente se fornecido
        if (dadosParciais.getPerfil() != null) {
            Perfil perfilParcial = dadosParciais.getPerfil();
            Perfil perfilExistente = usuario.getPerfil();

            if (perfilExistente != null) {
                if (perfilParcial.getBio() != null) {
                    perfilExistente.setBio(perfilParcial.getBio());
                }
                if (perfilParcial.getAvatarUrl() != null) {
                    perfilExistente.setAvatarUrl(perfilParcial.getAvatarUrl());
                }
            } else {
                perfilParcial.setUsuario(usuario);
                usuario.setPerfil(perfilParcial);
            }
        }

        return usuarioRepository.save(usuario);
    }

    public void excluir(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }

        try {
            usuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir usuário. Pode ter livros ou perfil associados.", e);
        }
    }
}
