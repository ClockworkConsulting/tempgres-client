package dk.cwconsult.tempgres;

import dk.cwconsult.tempgres.data.Address;
import dk.cwconsult.tempgres.data.Credentials;
import dk.cwconsult.tempgres.data.Database;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * Client for the postgresql-fixture-server.
 */
public class TempgresClient {

  /**
   * Create a temporary database.
   *
   * @param tempgresServerUrl the URL for the tempgres REST service.
   * @return a {@link Database} object containing all the necessary
   *   connection information for the newly created database.
   *
   * @throws MalformedURLException if the given URL is not valid.
   */
  public static Database createTemporaryDatabase(String tempgresServerUrl) throws IOException {
    HttpURLConnection con = (HttpURLConnection) (new URL(tempgresServerUrl)).openConnection();
    try {
      // Send the (empty) post request
      doEmptyPost(con);
      // We can only accept response code 200 for now.
      int responseCode = con.getResponseCode();
      if (responseCode != 200) {
        throw new TempgresException("Unexpected response code: " + responseCode);
      }
      // Read the response into a string. We assume an UTF-8 encoding.
      String responseBody = doReadResponseBody(con);
      // Reassemble response into a JDBC URL
      return parseBody(responseBody);
    } finally {
      // Try to disconnect; best effort only, so we swallow exceptions.
      try {
        con.disconnect();
      } catch (Exception e) {
        // Ignored
      }
    }
  }

  private static void doEmptyPost(HttpURLConnection connection) throws IOException {
    connection.setRequestMethod("POST");
    connection.setDoOutput(true);
    try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
      wr.flush();
      wr.close();
    }
  }

  private static String doReadResponseBody(HttpURLConnection connection) throws IOException {
    StringBuffer buf = new StringBuffer();
    try (InputStream inputStream = connection.getInputStream();
         Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
      // Hideously inefficient, but we're already going over the network and
      // the responses are very small.
      while (true) {
        int ch = reader.read();
        if (ch >= 0) {
          buf.append((char) ch);
        } else {
          break;
        }
      }
    }
    return buf.toString();
  }

  private static Database parseBody(String body) {
    // Split up the response.
    String allFields[] = body.split("\n");
    if (allFields.length != 5) {
      throw new IllegalArgumentException("Unexpected number of fields (" + allFields.length + "): Body was: [" + body + "]");
    }
    // Extract all the fields; mostly for readability when we create the JDBC URL
    String userName = allFields[0];
    String password = allFields[1];
    String host = allFields[2];
    String portString = allFields[3];
    String name = allFields[4];
    // Parse port
    int port;
    try {
      port = Integer.parseInt(portString, 10);
    } catch (NumberFormatException nfe) {
      throw new IllegalArgumentException("Port number is not a number: " + portString);
    }
    // Wrap up
    Address address = new Address(host, port);
    Credentials credentials = new Credentials(userName, password);
    Database database = new Database(
      credentials,
      address,
      name);
    return database;
  }

}
