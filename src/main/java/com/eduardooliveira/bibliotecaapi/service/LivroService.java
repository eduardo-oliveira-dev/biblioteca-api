package com.eduardooliveira.bibliotecaapi.service;

import com.eduardooliveira.bibliotecaapi.entity.Livro;
import com.eduardooliveira.bibliotecaapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Optional<Livro> buscarPorId(Long id) {
        return livroRepository.findById(id);
    }

    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    public Livro atualizarParcial(Long id, Livro dadosParciais) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (dadosParciais.getTitulo() != null) {
            livro.setTitulo(dadosParciais.getTitulo());
        }
        if (dadosParciais.getIsbn() != null) {
            livro.setIsbn(dadosParciais.getIsbn());
        }
        if (dadosParciais.getResumo() != null) {
            livro.setResumo(dadosParciais.getResumo());
        }
        if (dadosParciais.getAutores() != null) {
            livro.setAutores(dadosParciais.getAutores());
        }
        if (dadosParciais.getEditora() != null) {
            livro.setEditora(dadosParciais.getEditora());
        }
        if (dadosParciais.getGenero() != null) {
            livro.setGenero(dadosParciais.getGenero());
        }
        if (dadosParciais.getDataPublicacao() != null) {
            livro.setDataPublicacao(dadosParciais.getDataPublicacao());
        }
        if (dadosParciais.getNumeroPaginas() != null) {
            livro.setNumeroPaginas(dadosParciais.getNumeroPaginas());
        }
        if (dadosParciais.getUsuario() != null) livro.setUsuario(dadosParciais.getUsuario());


        return livroRepository.save(livro);
    }

    public void excluir(Long id) {

        if (!livroRepository.existsById(id)) {
            throw new RuntimeException("Livro não encontrado com ID: " + id);
        }
        try {
            livroRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir livro com ID: " + id, e);
        }
    }

}
