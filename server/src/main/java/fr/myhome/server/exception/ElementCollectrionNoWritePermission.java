package fr.myhome.server.exception;

public class ElementCollectrionNoWritePermission extends FunctionalException {

    public ElementCollectrionNoWritePermission() {
        super("You have no permit to write on this collection" );
    }

}