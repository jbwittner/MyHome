package fr.myhome.server.model.enumerate;

public enum CollectionPermissionEnum {
    ADMIN("ADMIN"),
    READ_WRITE("READ_WRITE"),
    READ("READ");

    private String value;

    CollectionPermissionEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    public static CollectionPermissionEnum fromValue(String value) {
    for (CollectionPermissionEnum b : CollectionPermissionEnum.values()) {
        if (b.value.equals(value)) {
        return b;
        }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}