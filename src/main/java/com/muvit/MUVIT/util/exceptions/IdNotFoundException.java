package com.muvit.MUVIT.util.exceptions;

public class IdNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE = "No hay registros en la entidad %s con el id sumunistrado";

    public IdNotFoundException(String nameEntity) {
        super(String.format(ERROR_MESSAGE, nameEntity));
    }
}