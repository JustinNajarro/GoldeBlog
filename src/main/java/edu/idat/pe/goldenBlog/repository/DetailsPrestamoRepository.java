package edu.idat.pe.goldenBlog.repository;

import edu.idat.pe.goldenBlog.model.DetailsPrestamo;
import edu.idat.pe.goldenBlog.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailsPrestamoRepository extends JpaRepository<DetailsPrestamo, Long> {
    List<DetailsPrestamo> findByPrestamo_IdPrestamo(Long idPrestamo);
}
