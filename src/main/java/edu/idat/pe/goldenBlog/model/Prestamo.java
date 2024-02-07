package edu.idat.pe.goldenBlog.model;

import lombok.Data;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Data
@Table(name = "Prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrestamo;

    private Date prestamoDate;

    private Date returnDate;

    private String statusPrestamo;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
}

