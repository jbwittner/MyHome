package fr.myhome.server.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import fr.myhome.server.generated.model.CollectionPermissionEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CollectionSumarryDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CollectionSumarryDTO   {
  @JsonProperty("collectionId")
  private Integer collectionId;

  @JsonProperty("collectionName")
  private String collectionName;

  @JsonProperty("permission")
  private CollectionPermissionEnum permission;

  public CollectionSumarryDTO collectionId(Integer collectionId) {
    this.collectionId = collectionId;
    return this;
  }

  /**
   * Get collectionId
   * @return collectionId
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getCollectionId() {
    return collectionId;
  }

  public void setCollectionId(Integer collectionId) {
    this.collectionId = collectionId;
  }

  public CollectionSumarryDTO collectionName(String collectionName) {
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

  public CollectionSumarryDTO permission(CollectionPermissionEnum permission) {
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
    CollectionSumarryDTO collectionSumarryDTO = (CollectionSumarryDTO) o;
    return Objects.equals(this.collectionId, collectionSumarryDTO.collectionId) &&
        Objects.equals(this.collectionName, collectionSumarryDTO.collectionName) &&
        Objects.equals(this.permission, collectionSumarryDTO.permission);
  }

  @Override
  public int hashCode() {
    return Objects.hash(collectionId, collectionName, permission);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CollectionSumarryDTO {\n");
    
    sb.append("    collectionId: ").append(toIndentedString(collectionId)).append("\n");
    sb.append("    collectionName: ").append(toIndentedString(collectionName)).append("\n");
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

