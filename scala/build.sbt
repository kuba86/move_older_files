ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.3"

lazy val root = (project in file("."))
  .settings(name := "scala", idePackagePrefix := Some("com.kuba86"))

libraryDependencies += "com.lihaoyi" %% "os-lib" % "0.8.1"
libraryDependencies += "com.lihaoyi" %% "pprint" % "0.7.3"
