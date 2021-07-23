package com.aps.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Registro não encontrado");
    }

    public NotFoundException(Class clazz) {
        super("Registro da classe " + clazz.getCanonicalName() + " não foi encontrado");
    }
}
