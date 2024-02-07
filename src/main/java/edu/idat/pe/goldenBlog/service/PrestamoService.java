package edu.idat.pe.goldenBlog.service;

import edu.idat.pe.goldenBlog.Utils.UtilsLibrary;
import edu.idat.pe.goldenBlog.model.Book;
import edu.idat.pe.goldenBlog.model.DetailsPrestamo;
import edu.idat.pe.goldenBlog.model.Prestamo;
import edu.idat.pe.goldenBlog.repository.BookRepository;
import edu.idat.pe.goldenBlog.repository.DetailsPrestamoRepository;
import edu.idat.pe.goldenBlog.repository.PrestamoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {

    @Autowired
    public PrestamoRepository prestamoRepository;

    @Autowired
    public DetailsPrestamoRepository detailsPrestamoRepository;

    @Autowired
    public BookRepository bookRepository;

    public Collection<Prestamo> getAllPrestamos(){
        return (Collection<Prestamo>) prestamoRepository.findAll();
    }

    // FALTA VALIDAR QUE EL OBJETO PRESTAMO ESTE COMPLETO
    // FALTA VALIDAR QUE LA LISTA DE LIBROS NO ESTE VACIA
    @Transactional
    public ResponseEntity<String> savePrestamoWithDetail(Prestamo prestamo, List<Book> books) {
        if (books == null || books.isEmpty()) {
            return UtilsLibrary.getResponseEntity("La lista de libros está vacía", HttpStatus.BAD_REQUEST);
        }

        prestamoRepository.save(prestamo);

        for (Book book : books) {
            DetailsPrestamo detailPrestamo = new DetailsPrestamo();
            detailPrestamo.setPrestamo(prestamo);
            detailPrestamo.setBook(book);
            detailsPrestamoRepository.save(detailPrestamo);

            book.setStatusBook("No disponible");
            bookRepository.save(book);
        }

        return UtilsLibrary.getResponseEntity("El préstamo fue guardado con éxito", HttpStatus.CREATED);
    }


    public Collection<Prestamo> findPrestamoByIdUser(Long idUser){
        return (Collection<Prestamo>) prestamoRepository.findByUser_IdUser(idUser);
    }

    public ResponseEntity<?> findPrestamoByIdPrestamo (Long idPrestamo){
        Optional<Prestamo> prestamo = prestamoRepository.findById(idPrestamo);
        if(prestamo.isPresent()){
            return new ResponseEntity<>(prestamo, HttpStatus.OK);
        }else{
            return UtilsLibrary.getResponseEntity("No existe el prestamo con id: ", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deletePrestamo(@PathVariable Long idPrestamo){
        Optional<Prestamo> prestamo = prestamoRepository.findById(idPrestamo);
        if (prestamo.isEmpty()){
            return UtilsLibrary.getResponseEntity("No existe el prestamo con id: " + idPrestamo, HttpStatus.BAD_REQUEST);
        }else{
            return UtilsLibrary.getResponseEntity("El prestamo fue eliminado con exito", HttpStatus.OK);
        }
    }

}
