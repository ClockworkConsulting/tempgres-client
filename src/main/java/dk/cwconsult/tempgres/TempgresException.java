package dk.cwconsult.tempgres;

/**
 * An exception occurred while creating temporary database.
 */
public class TempgresException extends RuntimeException {

  public TempgresException(String message) {
    super(message);
  }

}
