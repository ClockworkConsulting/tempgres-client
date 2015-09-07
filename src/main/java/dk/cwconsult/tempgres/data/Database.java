package dk.cwconsult.tempgres.data;

/**
 * Information necessary to connect to a database.
 */
public class Database {

  /**
   * Credentials to use for connecting to the database.
   */
  private final Credentials credentials;

  /**
   * Address of the PostgreSQL server.
   */
  private final Address address;

  /**
   * Name of the temporary database.
   */
  private final String name;

  /**
   * Create a new {@link Database} instance.
   */
  public Database(Credentials credentials, Address address, String name) {
    this.credentials = credentials;
    this.address = address;
    this.name = name;
  }

  /**
   * Get the credentials.
   */
  public Credentials getCredentials() {
    return credentials;
  }

  /**
   * Get the name of the temporary database.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the address of the PostgreSQL instance.
   */
  public Address getAddress() {
    return address;
  }

  /**
   * Get the JDBC URL for the database. The URL does <b>not</b>
   * contain the credentials. Those should be retrieved using
   * {@link #getCredentials()} method and supplied separately to
   * the database framework that you're using.
   */
  public String getUrl() {
    StringBuilder url = new StringBuilder(128);
    url.append("jdbc:postgresql://");
    url.append(address.host);
    url.append(":");
    url.append(address.port);
    url.append("/");
    url.append(name);
    return url.toString();
  }

}
