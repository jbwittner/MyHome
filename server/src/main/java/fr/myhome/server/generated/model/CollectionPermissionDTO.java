package fr.myhome.server.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import fr.myhome.server.generated.model.CollectionPermissionEnum;
import fr.myhome.server.generated.model.UserDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CollectionPermissionDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CollectionPermissionDTO   {
  @JsonProperty("userDTO")
  private UserDTO userDTO;

  @JsonProperty("permission")
  private CollectionPermissionEnum permission;

  public CollectionPermissionDTO userDTO(UserDTO userDTO) {
    this.userDTO = userDTO;
    return this;
  }

  /**
   * Get userDTO
   * @return userDTO
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public UserDTO getUserDTO() {
    return userDTO;
  }

  public void setUserDTO(UserDTO userDTO) {
    this.userDTO = userDTO;
  }

  public CollectionPermissionDTO permission(CollectionPermissionEnum permission) {
    this.permission = permission;
    return this;
  }

  /**
   * Get permission
   * @return permission
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public CollectionPermissionEnum getPermission() {
    return permission;
  }

  public void setPermission(CollectionPermissionEnum permission) {
    this.permission = permission;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CollectionPermissionDTO collectionPermissionDTO = (CollectionPermissionDTO) o;
    return Objects.equals(this.userDTO, collectionPermissionDTO.userDTO) &&
        Objects.equals(this.permission, collectionPermissionDTO.permission);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userDTO, permission);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CollectionPermissionDTO {\n");
    
    sb.append("    userDTO: ").append(toIndentedString(userDTO)).append("\n");
    sb.append("    permission: ").append(toIndentedString(permission)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

