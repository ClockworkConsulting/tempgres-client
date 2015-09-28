# Intro

`tempgres-client` provides a simple way for JVM-based programs to use
the
[tempgres-server](https://github.com/ClockworkConsulting/tempgres-server)
REST service for creating temporary PostgreSQL databases for tests.

# Usage

To get started, add `tempgres-client` as a dependency in your
project. You'll probably want a "test"-scoped dependency.

### Maven

    <dependency>
        <groupId>dk.cwconsult</groupId>
        <artifactId>tempgres-client</artifactId>
        <version>1.1.0</version>
        <scope>test</scope>
    </dependency>

### SBT

    libraryDependencies += "dk.cwconsult" % "tempgres-client" % "1.1.0" % "test"

## Example (Java)

```java
package dk.cwconsult;

import dk.cwconsult.tempgres.TempgresClient;
import dk.cwconsult.tempgres.data.Database;

import java.io.IOException;

public class TempgresExample {

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    // URL for the REST service; you'll probably want this to be
    // retrieved via an application configuration file and/or via
    // a Java System Property.
    String url = "http://localhost:8080";
    // Create the temporary database
    Database database = TempgresClient.createTemporaryDatabase(url);
    // Load PostgreSQL JDBC driver
    Class.forName("org.postgresql.Driver");
    // Display the connection information; here's where you would initialize
    // your connection pool, etc.
    System.out.println("Database JDBC URL: " + database.getUrl());
    System.out.println("User name: " + database.getCredentials().getUserName());
    System.out.println("Password: " + database.getCredentials().getPassword());
    ...;
  }

}
```

## Example (Scala)

The below example uses [ScalikeJDBC](http://scalikejdbc.org), but feel
free to use anything you like to connect to the database.

```scala
import dk.cwconsult.tempgres.TempgresClient
import scalikejdbc._

object Example {

  def main(args: Array[String]): Unit = {
    // URL for the REST service; you'll probably want this to be
    // retrieved via an application configuration file and/or via
    // a Java System Property.
    val url = "http://localhost:8080"
    // Create the temporary database
    val database = TempgresClient.createTemporaryDatabase(url)
    // Load PostgreSQL JDBC driver
    Class.forName("org.postgresql.Driver")
    // Initialize the connection pool
    ConnectionPool.singleton(
      database.getUrl,
      database.getCredentials.getUserName,
      database.getCredentials.getPassword)
    // Use the connection pool
    ... // Your code goes here
  }

}
```

# Copyright and License

This code is provided under the [BSD 2-clause license](https://github.com/ClockworkConsulting/tempgres-client/blob/master/LICENSE)
