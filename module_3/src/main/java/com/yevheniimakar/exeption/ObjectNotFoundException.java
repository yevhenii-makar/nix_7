package com.yevheniimakar.exeption;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(final String massage) {
        super(massage);
    }
}
