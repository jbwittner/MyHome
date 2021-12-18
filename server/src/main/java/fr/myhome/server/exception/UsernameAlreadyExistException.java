package fr.myhome.server.exception;

public class UsernameAlreadyExistException extends FunctionalException {

    public UsernameAlreadyExistException(final String userName) {
        super("The username " + userName + " are already used");
    }

}