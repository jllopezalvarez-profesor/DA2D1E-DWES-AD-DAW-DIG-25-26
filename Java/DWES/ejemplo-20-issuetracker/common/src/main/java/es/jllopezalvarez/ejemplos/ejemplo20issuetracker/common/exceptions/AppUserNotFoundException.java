package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.exceptions;

public class AppUserNotFoundException extends RuntimeException {
    public AppUserNotFoundException() {
    }

    public AppUserNotFoundException(String message) {
        super(message);
    }

    public AppUserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppUserNotFoundException(Throwable cause) {
        super(cause);
    }
}
