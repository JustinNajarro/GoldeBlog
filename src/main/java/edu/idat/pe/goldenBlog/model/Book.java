package edu.idat.pe.goldenBlog.model;

import lombok.Data;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Data
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBook;

    private String nameBook;

    private String author;

    private String description;

    private Date publicationDate;

    private String statusBook;

    private String picture;

    @ManyToOne
    @JoinColumn(name = "idCategory")
    private Category category;
}
