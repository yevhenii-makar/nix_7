package com.yevheniimakar.beltcutting.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public final class BeltCuttingExceptions {

    private BeltCuttingExceptions() {}

    public static ResponseStatusException userNotFound(String email) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "BeltCuttingUser with email " + email + " not found");
    }

    public static ResponseStatusException userNotFound(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "BeltCuttingUser with id " + id + " not found");
    }

    public static ResponseStatusException taskNotFound(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with id " + id + " not found");
    }

    public static ResponseStatusException cardNotFound(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Card with id " + id + " not found");
    }

    public static ResponseStatusException pieceNotFound(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Piece with id " + id + " not found");
    }
}
