package fr.myhome.server.exception;

public class UserNotExistException extends FunctionalException {
    
    public UserNotExistException(final String userName) {
        super("The user with username '" + userName + "' not exist");
    }
}