package com.eduardooliveira.bibliotecaapi.service;

import com.eduardooliveira.bibliotecaapi.entity.Perfil;
import com.eduardooliveira.bibliotecaapi.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public Perfil salvar(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    public List<Perfil> listarTodos() {
        return perfilRepository.findAll();
    }

    public Optional<Perfil> buscarPorId(Long id) {
        return perfilRepository.findById(id);
    }

    public Perfil atualizarParcial(Long id, Perfil dadosParciais) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado com ID: " + id));

        if (dadosParciais.getBio() != null) {
            perfil.setBio(dadosParciais.getBio());
        }
        if (dadosParciais.getAvatarUrl() != null) {
            perfil.setAvatarUrl(dadosParciais.getAvatarUrl());
        }
        if (dadosParciais.getUsuario() != null) {
            perfil.setUsuario(dadosParciais.getUsuario());
        }

        return perfilRepository.save(perfil);
    }

    public void excluir(Long id) {
        if (!perfilRepository.existsById(id)) {
            throw new RuntimeException("Perfil não encontrado com ID: " + id);
        }

        try {
            perfilRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir perfil com ID: " + id, e);
        }
    }
}