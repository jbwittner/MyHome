package fr.myhome.server.exception;

public class TokenMatchException extends FunctionalException {

    public TokenMatchException() {
        super("The refresh token doesn't match" );
    }

}