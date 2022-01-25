package fr.myhome.server.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import fr.myhome.server.generated.model.CollectionPermissionDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CollectionDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CollectionDTO   {
  @JsonProperty("collectionName")
  private String collectionName;

  @JsonProperty("permissions")
  @Valid
  private List<CollectionPermissionDTO> permissions = new ArrayList<CollectionPermissionDTO>();

  public CollectionDTO collectionName(String collectionName) {
    this.collectionName = collectionName;
    return this;
  }

  /**
   * Get collectionName
   * @return collectionName
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getCollectionName() {
    return collectionName;
  }

  public void setCollectionName(String collectionName) {
    this.collectionName = collectionName;
  }

  public CollectionDTO permissions(List<CollectionPermissionDTO> permissions) {
    this.permissions = permissions;
    return this;
  }

  public CollectionDTO addPermissionsItem(CollectionPermissionDTO permissionsItem) {
    this.permissions.add(permissionsItem);
    return this;
  }

  /**
   * Get permissions
   * @return permissions
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<CollectionPermissionDTO> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<CollectionPermissionDTO> permissions) {
    this.permissions = permissions;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CollectionDTO collectionDTO = (CollectionDTO) o;
    return Objects.equals(this.collectionName, collectionDTO.collectionName) &&
        Objects.equals(this.permissions, collectionDTO.permissions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(collectionName, permissions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CollectionDTO {\n");
    
    sb.append("    collectionName: ").append(toIndentedString(collectionName)).append("\n");
    sb.append("    permissions: ").append(toIndentedString(permissions)).append("\n");
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

