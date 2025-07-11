package com.eduardooliveira.bibliotecaapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String titulo;

    @Column(unique = true, length = 13)
    private String isbn;

    @Column(length = 1000)
    private String resumo;

    @ManyToMany
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<Autor> autores;

    @Column(name = "data_publicacao")
    private LocalDate dataPublicacao;

    @Column(nullable = false)
    private Integer numeroPaginas;

    @Column
    private String editora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GeneroLivro genero;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return Objects.equals(id, livro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
