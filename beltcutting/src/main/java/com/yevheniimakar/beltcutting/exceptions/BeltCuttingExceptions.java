package com.yevheniimakar.beltcutting.exceptions;

import com.yevheniimakar.beltcutting.exceptions.auth.InvalidRefreshTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public final class BeltCuttingExceptions {

    private BeltCuttingExceptions() {
    }

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

    public static ResponseStatusException manufacturerNotFound(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Manufacturer with id " + id + " not found");
    }

    public static ResponseStatusException unitNotFound(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Unit with id " + id + " not found");
    }

    public static ResponseStatusException pieceNotFound(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Piece with id " + id + " not found");
    }

    public static ResponseStatusException pieceNotEnough(int pieceNumber, Long pieceId, Long cardId) {
        return new ResponseStatusException(HttpStatus.CONFLICT, "Piece number " + pieceNumber
                + "with id " + pieceId + " of card with id " + cardId + " is not enough");
    }

    public static ResponseStatusException countCardNotEnough(Long cardId) {
        return new ResponseStatusException(HttpStatus.CONFLICT, "Count of card " + cardId + " is not enough");
    }

    public static ResponseStatusException duplicateEmail(String email) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email " + email + " already taken");
    }

    public static ResponseStatusException invalidRefreshToken(InvalidRefreshTokenException cause) {
        return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Refresh token is invalid! It may have been rotated, invalidated or expired naturally", cause);
    }

    public static ResponseStatusException notHavingNecessaryPermissionsForGetTask(Long id, String name) {
        return new ResponseStatusException(HttpStatus.FORBIDDEN, "Task with id " + id + " not available for user " + name);
    }

    public static ResponseStatusException emptyListEquipment(Long id) {
        return new ResponseStatusException(HttpStatus.CONFLICT, "Equipment for task " + id + "mast be not empty");
    }

    public static ResponseStatusException notHavingNecessaryPermissionsToDoEquipmentTemplate() {
        return new ResponseStatusException(HttpStatus.FORBIDDEN, "Doт`t have permission to do getEquipmentTemplate");
    }

    public static ResponseStatusException countMustByPosetive() {
        return new ResponseStatusException(HttpStatus.CONFLICT, "count mast be positive");
    }
}
