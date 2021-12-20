package fr.myhome.server.exception;

public class NoRefreshTokenCookie extends FunctionalException {

    private static final long serialVersionUID = 1L;

    public NoRefreshTokenCookie(){
        super(String.format("The request doesn't send refresh cookies !"));
    }
    
}