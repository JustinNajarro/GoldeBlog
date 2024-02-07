package edu.idat.pe.goldenBlog.repository;


import edu.idat.pe.goldenBlog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Método para verificar la duplicación de un usuario con el mismo nombre y apellido
    Optional<User> findByNameUserAndLastnameUser(String nameUser, String lastnameUser);

    // Método para obtener todos los usuarios con un rol específico
    List<User> findByRole_NameRol(String nameRol);

    Optional<User> findByEmailUser(String emailUser);

}
