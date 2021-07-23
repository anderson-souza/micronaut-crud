package com.aps.exceptions;

public class NotAllowedException extends RuntimeException {

    public NotAllowedException() {
        super("Função não permitida");
    }
}
