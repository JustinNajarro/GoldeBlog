package edu.idat.pe.goldenBlog.exception;

import java.io.Serial;
import java.io.Serializable;

public class AuthenticationException extends RuntimeException implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public AuthenticationException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
