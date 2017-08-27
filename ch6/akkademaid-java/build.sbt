name := "akkademaid-java"

organization := "com.akkademy-db"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.12.2"

lazy val akkaVersion = "2.5.4"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
  "com.typesafe.akka" %% "akka-contrib" % akkaVersion,
  "com.syncthemall" % "boilerpipe" % "1.2.2",
  "com.jason-goodwin" % "better-monads" % "0.2.0",
  "com.akkademy-db" %% "akkademy-db-java" % "0.1.0-SNAPSHOT"
)

mappings in (Compile, packageBin) ~= { _.filterNot { case (_, name) =>
  Seq("application.conf").contains(name)
}}
