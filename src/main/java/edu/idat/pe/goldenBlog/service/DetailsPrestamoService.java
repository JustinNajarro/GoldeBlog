package edu.idat.pe.goldenBlog.service;

import edu.idat.pe.goldenBlog.Utils.UtilsLibrary;
import edu.idat.pe.goldenBlog.model.DetailsPrestamo;
import edu.idat.pe.goldenBlog.repository.DetailsPrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Optional;

@Service
public class DetailsPrestamoService {

    @Autowired
    DetailsPrestamoRepository detailsPrestamoRepository;

    public Collection<DetailsPrestamo> getAllDetailsPrestamos(){
        return (Collection<DetailsPrestamo>) detailsPrestamoRepository.findAll();
    }

    public Collection<DetailsPrestamo> findDetailsByIdPrestamo(Long idPrestamo){
        return (Collection<DetailsPrestamo>) detailsPrestamoRepository.findByPrestamo_IdPrestamo(idPrestamo);
    }

    public ResponseEntity<?> deleteDetailsPrestamo(@PathVariable Long idDetailsPrestamo){
        Optional<DetailsPrestamo> detailsPrestamo = detailsPrestamoRepository.findById(idDetailsPrestamo);
        if(detailsPrestamo.isEmpty()){
            return UtilsLibrary.getResponseEntity("No existe este detalle libro con id: "+idDetailsPrestamo, HttpStatus.BAD_REQUEST);
        }else {
            return UtilsLibrary.getResponseEntity("El detalle prestamo fue eliminado con exito", HttpStatus.OK);
        }
    }
}
