package fr.myhome.server.exception;

public class CollectionNotExistException extends FunctionalException {

    public CollectionNotExistException(Integer collectionId) {
        super(String.format("The collection with the id : %d doesn't exist", collectionId));
    }

}
