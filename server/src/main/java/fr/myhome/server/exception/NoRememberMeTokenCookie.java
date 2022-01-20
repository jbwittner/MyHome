package fr.myhome.server.exception;

public class NoRememberMeTokenCookie extends FunctionalException {

    private static final long serialVersionUID = 1L;

    public NoRememberMeTokenCookie(){
        super(String.format("The request doesn't send remember me cookie !"));
    }
    
}