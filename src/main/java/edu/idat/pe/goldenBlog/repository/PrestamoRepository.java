package edu.idat.pe.goldenBlog.repository;

import edu.idat.pe.goldenBlog.model.Book;
import edu.idat.pe.goldenBlog.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByUser_IdUser(Long idUser);
}
