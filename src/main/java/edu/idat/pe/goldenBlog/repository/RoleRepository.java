package edu.idat.pe.goldenBlog.repository;

import edu.idat.pe.goldenBlog.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNameRol(String nameRol);
}
