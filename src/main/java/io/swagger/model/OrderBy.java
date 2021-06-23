package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * OrderBy
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-22T11:03:20.924Z[GMT]")


public class OrderBy   {
  @JsonProperty("column")
  private String column = "id";

  @JsonProperty("ascendant")
  private Boolean ascendant = true;

  public OrderBy column(String column) {
    this.column = column;
    return this;
  }

  /**
   * Get column
   * @return column
   **/
  @Schema(description = "")
  
    public String getColumn() {
    return column;
  }

  public void setColumn(String column) {
    this.column = column;
  }

  public OrderBy ascendant(Boolean ascendant) {
    this.ascendant = ascendant;
    return this;
  }

  /**
   * Get ascendant
   * @return ascendant
   **/
  @Schema(description = "")
  
    public Boolean isAscendant() {
    return ascendant;
  }

  public void setAscendant(Boolean ascendant) {
    this.ascendant = ascendant;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderBy orderBy = (OrderBy) o;
    return Objects.equals(this.column, orderBy.column) &&
        Objects.equals(this.ascendant, orderBy.ascendant);
  }

  @Override
  public int hashCode() {
    return Objects.hash(column, ascendant);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderBy {\n");
    
    sb.append("    column: ").append(toIndentedString(column)).append("\n");
    sb.append("    ascendant: ").append(toIndentedString(ascendant)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
