ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

ThisBuild / assemblyMergeStrategy := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x                             => MergeStrategy.first
}

lazy val root = (project in file("."))
  .settings(name := "scala", idePackagePrefix := Some("com.kuba86"))

libraryDependencies += "com.lihaoyi" %% "os-lib" % "0.8.1"
libraryDependencies += "com.lihaoyi" %% "pprint" % "0.7.3"
libraryDependencies += "com.github.alexarchambault" %% "case-app" % "2.1.0-M15"
