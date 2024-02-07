package edu.idat.pe.goldenBlog.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UtilsLibrary {

    private UtilsLibrary(){

    }

    public static ResponseEntity<String> getResponseEntity(String message, HttpStatus httpStatus){
        return new ResponseEntity<String>("Mensaje : " + message, httpStatus);
    }
}
