package com.bookstore.jpa.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String title;
    private String author;
    private int pages;

    @ManyToOne
    @JoinColumn
    private Publisher publisher;

    @ManyToMany
    @JoinTable(//cria uma nova relação no banco de dados(caracteritica do relacionamento N-M)
            name = "book_author",//Nome da tabela auxiliar
            joinColumns = @JoinColumn(name = "book_id"),//chave estrangeira de Book
            inverseJoinColumns = @JoinColumn(name = "author_id"))//Chave estrangeira de Author
    private Set<Author> authors = new HashSet<>();

    @OneToOne(mappedBy = "book",cascade = CascadeType.ALL)
    private Review review;



}
