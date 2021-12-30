package fr.myhome.server.exception;

public class BadTokenException extends FunctionalException {

    public BadTokenException(final String message) {
        super("Problem with the token : " + message );
    }

}