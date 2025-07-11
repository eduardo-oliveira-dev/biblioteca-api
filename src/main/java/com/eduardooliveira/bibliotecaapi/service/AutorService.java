package com.eduardooliveira.bibliotecaapi.service;

import com.eduardooliveira.bibliotecaapi.entity.Autor;
import com.eduardooliveira.bibliotecaapi.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public Autor salvar(Autor autor) {
        return autorRepository.save(autor);
    }

    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    public Optional<Autor> buscarPorId(Long id) {
        return autorRepository.findById(id);
    }

    public Autor atualizarParcial(Long id, Autor dadosParciais) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com ID: " + id));

        if (dadosParciais.getNome() != null) {
            autor.setNome(dadosParciais.getNome());
        }
        if (dadosParciais.getNacionalidade() != null) {
            autor.setNacionalidade(dadosParciais.getNacionalidade());
        }

        return autorRepository.save(autor);
    }

    public void excluir(Long id) {
        if (!autorRepository.existsById(id)) {
            throw new RuntimeException("Autor não encontrado com ID: " + id);
        }

        try {
            autorRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir autor. Pode estar vinculado a livros.", e);
        }
    }

}