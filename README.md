# Intro

`tempgres-client` provides a simple way for JVM-based programs to use
the
[tempgres-server](https://github.com/ClockworkConsulting/tempgres-server)
REST service for creating temporary PostgreSQL databases for tests.

# Usage

1. Add a dependency on `tempgres-client` in your build (Maven, SBT, ...).
2. Create an instance of the `dk.cwconsult.tempgres.TempgresClient` class.
3. Invoke the `createTemporaryDatabase` method on the class.
4. Done! You get back an object describing all the parameters you need to use to connect to the freshly created database.

All relevant classes/methods have JavaDoc strings which your IDE
should be able to show you if you choose to download the source
bundles.

# Copyright and License

This code is provided under the [BSD 2-clause license](https://github.com/ClockworkConsulting/tempgres-client/blob/master/LICENSE)
