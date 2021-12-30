package fr.myhome.server.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import fr.myhome.server.generated.model.TokenTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TokenDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class TokenDTO   {
  @JsonProperty("jwt")
  private String jwt;

  @JsonProperty("duration")
  private Long duration;

  @JsonProperty("type")
  private TokenTypeEnum type;

  public TokenDTO jwt(String jwt) {
    this.jwt = jwt;
    return this;
  }

  /**
   * Get jwt
   * @return jwt
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getJwt() {
    return jwt;
  }

  public void setJwt(String jwt) {
    this.jwt = jwt;
  }

  public TokenDTO duration(Long duration) {
    this.duration = duration;
    return this;
  }

  /**
   * Get duration
   * @return duration
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getDuration() {
    return duration;
  }

  public void setDuration(Long duration) {
    this.duration = duration;
  }

  public TokenDTO type(TokenTypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public TokenTypeEnum getType() {
    return type;
  }

  public void setType(TokenTypeEnum type) {
    this.type = type;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TokenDTO tokenDTO = (TokenDTO) o;
    return Objects.equals(this.jwt, tokenDTO.jwt) &&
        Objects.equals(this.duration, tokenDTO.duration) &&
        Objects.equals(this.type, tokenDTO.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(jwt, duration, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TokenDTO {\n");
    
    sb.append("    jwt: ").append(toIndentedString(jwt)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

