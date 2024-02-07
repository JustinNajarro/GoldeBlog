package edu.idat.pe.goldenBlog.model;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    private String nameCategory;
}
