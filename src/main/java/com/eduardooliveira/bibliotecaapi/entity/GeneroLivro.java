package com.eduardooliveira.bibliotecaapi.entity;

public enum GeneroLivro {

    FICCAO("Ficção"),
    NAO_FICCAO("Não Ficção"),
    ROMANCE("Romance"),
    SUSPENSE("Suspense"),
    FANTASIA("Fantasia"),
    BIOGRAFIA("Biografia"),
    HISTORIA("História"),
    CIENCIA("Ciência"),
    TECNOLOGIA("Tecnologia"),
    INFANTIL("Infantil");

    private final String descricao;

    GeneroLivro(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
