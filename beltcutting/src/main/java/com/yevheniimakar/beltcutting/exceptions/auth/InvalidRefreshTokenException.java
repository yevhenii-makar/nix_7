package com.yevheniimakar.beltcutting.exceptions.auth;

public class InvalidRefreshTokenException extends Exception {
    public InvalidRefreshTokenException() {
        super();
    }

    public InvalidRefreshTokenException(Throwable cause) {
        super(cause);
    }
}
