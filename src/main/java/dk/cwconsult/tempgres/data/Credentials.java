package dk.cwconsult.tempgres.data;

/**
 * Credentials to supply for authentication.
 */
public class Credentials {

  /**
   * User name.
   */
  private final String userName;

  /**
   * Password.
   */
  private final String password;

  /**
   * Create a new {@linkplain Credentials} instance.
   */
  public Credentials(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }

  /**
   * Get the user name.
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Get the password.
   */
  public String getPassword() {
    return password;
  }

}
