ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.14"

ThisBuild / assemblyMergeStrategy := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x                             => MergeStrategy.first
}

lazy val root = (project in file("."))
  .settings(name := "scala", idePackagePrefix := Some("com.kuba86"))

libraryDependencies += "com.lihaoyi" %% "os-lib" % "0.10.5"
libraryDependencies += "com.lihaoyi" %% "pprint" % "0.9.0"
libraryDependencies += "com.github.alexarchambault" %% "case-app" % "2.1.0-M29"
