//
// Project metadata
//

name := "tempgres-client"

organization := "dk.cwconsult"

version := "1.0"

//
// sbt-pgp settings
//

useGpg := true

//
// This is a Java-only project, so we don't need the scala
// library nor the artifact name mangling.
//

crossPaths := false

autoScalaLibrary := false

//
// Compiler settings
//

javacOptions ++= Seq("-source", "1.7")
