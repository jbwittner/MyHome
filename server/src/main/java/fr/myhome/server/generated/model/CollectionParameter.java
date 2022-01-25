package fr.myhome.server.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import fr.myhome.server.generated.model.CollectionPermissionParameter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CollectionParameter
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CollectionParameter   {
  @JsonProperty("collectionName")
  private String collectionName;

  @JsonProperty("permissions")
  @Valid
  private List<CollectionPermissionParameter> permissions = null;

  public CollectionParameter collectionName(String collectionName) {
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

  public CollectionParameter permissions(List<CollectionPermissionParameter> permissions) {
    this.permissions = permissions;
    return this;
  }

  public CollectionParameter addPermissionsItem(CollectionPermissionParameter permissionsItem) {
    if (this.permissions == null) {
      this.permissions = new ArrayList<CollectionPermissionParameter>();
    }
    this.permissions.add(permissionsItem);
    return this;
  }

  /**
   * Get permissions
   * @return permissions
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<CollectionPermissionParameter> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<CollectionPermissionParameter> permissions) {
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
    CollectionParameter collectionParameter = (CollectionParameter) o;
    return Objects.equals(this.collectionName, collectionParameter.collectionName) &&
        Objects.equals(this.permissions, collectionParameter.permissions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(collectionName, permissions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CollectionParameter {\n");
    
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

