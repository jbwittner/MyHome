package fr.myhome.server.exception;

public class ElementCollectionNoReadPermission extends FunctionalException {

    public ElementCollectionNoReadPermission() {
        super("You have no permit to read on this collection" );
    }

}