package fr.myhome.server.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets CollectionPermissionEnum
 */
public enum CollectionPermissionEnum {
  
  ADMIN("ADMIN"),
  
  READ_WRITE("READ_WRITE"),
  
  READ("READ");

  private String value;

  CollectionPermissionEnum(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CollectionPermissionEnum fromValue(String value) {
    for (CollectionPermissionEnum b : CollectionPermissionEnum.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

