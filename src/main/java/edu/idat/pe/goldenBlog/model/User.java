package edu.idat.pe.goldenBlog.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    private String nameUser;

    private String lastnameUser;

    private String dniUser;

    private String emailUser;

    private String passwordUser;

    @ManyToOne
    @JoinColumn(name = "idRol")
    private Role role;

    @Transient
    public Long getIdRol() {
        return role != null ? role.getIdRol() : null;
    }

}