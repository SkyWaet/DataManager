package ru.vtb.slepenkov.datamanager.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

/**
 * UserWithDescription
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-22T11:03:20.924Z[GMT]")


public class UserWithDescription   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("login")
  private String login = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("desc")
  private String desc = null;

  public UserWithDescription id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * minimum: 0
   * @return id
   **/
  @Schema(description = "")
  
  @Min(0L)  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserWithDescription login(String login) {
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

  public UserWithDescription password(String password) {
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

  public UserWithDescription email(String email) {
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

  public UserWithDescription desc(String desc) {
    this.desc = desc;
    return this;
  }

  /**
   * description of user
   * @return desc
   **/
  @Schema(description = "description of user")
  
  @Size(max=1536)   public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserWithDescription userWithDescription = (UserWithDescription) o;
    return Objects.equals(this.id, userWithDescription.id) &&
        Objects.equals(this.login, userWithDescription.login) &&
        Objects.equals(this.password, userWithDescription.password) &&
        Objects.equals(this.email, userWithDescription.email) &&
        Objects.equals(this.desc, userWithDescription.desc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, login, password, email, desc);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserWithDescription {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    login: ").append(toIndentedString(login)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    desc: ").append(toIndentedString(desc)).append("\n");
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
