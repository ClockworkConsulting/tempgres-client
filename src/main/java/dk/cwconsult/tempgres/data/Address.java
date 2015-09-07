package dk.cwconsult.tempgres.data;

/**
 * Address of a PostgreSQL database instance.
 */
public class Address {

  /**
   * Host name or IP to use for connecting to the database.
   */
  public final String host;

  /**
   * Port to use for connecting to the database.
   */
  public final int port;

  /**
   * Create a new {@link Address} instance.
   */
  public Address(String host, int port) {
    this.host = host;
    this.port = port;
  }

}
