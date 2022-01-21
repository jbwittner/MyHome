package fr.myhome.server.exception;

public class TokenMatchException extends FunctionalException {

    public TokenMatchException() {
        super("The remember me token doesn't match" );
    }

}