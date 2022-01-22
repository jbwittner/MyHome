package fr.myhome.server.exception;

public class ElementCollectrionNoAdminPermission extends FunctionalException {

    public ElementCollectrionNoAdminPermission() {
        super("You are not the admin of this collection" );
    }

}