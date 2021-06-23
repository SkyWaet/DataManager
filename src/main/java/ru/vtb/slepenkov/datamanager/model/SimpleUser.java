package ru.vtb.slepenkov.datamanager.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

/**
 * SimpleUser
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-22T11:03:20.924Z[GMT]")


public class SimpleUser   {
  @JsonProperty("login")
  private String login = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("description")
  private String description = null;

  public SimpleUser login(String login) {
    this.login = login;
    return this;
  }

  /**
   * login of the user
   * @return login
   **/
  @Schema(required = true, description = "login of the user")
      @NotNull

  @Size(min=6,max=20)   public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public SimpleUser password(String password) {
    this.password = password;
    return this;
  }

  /**
   * password of the user
   * @return password
   **/
  @Schema(required = true, description = "password of the user")
      @NotNull

    public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public SimpleUser email(String email) {
    this.email = email;
    return this;
  }

  /**
   * user email
   * @return email
   **/
  @Schema(description = "user email")
  
    public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public SimpleUser description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  @Schema(description = "")
  
  @Size(max=1536)   public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SimpleUser simpleUser = (SimpleUser) o;
    return Objects.equals(this.login, simpleUser.login) &&
        Objects.equals(this.password, simpleUser.password) &&
        Objects.equals(this.email, simpleUser.email) &&
        Objects.equals(this.description, simpleUser.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(login, password, email, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SimpleUser {\n");
    
    sb.append("    login: ").append(toIndentedString(login)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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
