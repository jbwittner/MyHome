package fr.myhome.server.exception;

public class NoAccessTokenCookie extends FunctionalException {

    private static final long serialVersionUID = 1L;

    public NoAccessTokenCookie(final String uri){
        super(String.format("The request doesn't send access cookies to access : %s !", uri));
    }
    
}