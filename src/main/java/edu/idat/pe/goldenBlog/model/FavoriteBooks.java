package edu.idat.pe.goldenBlog.model;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "Favorite_books")
public class FavoriteBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFavoriteBooks;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idBook")
    private Book book;
}
