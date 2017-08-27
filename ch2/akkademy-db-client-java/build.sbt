name := "akkademy-db-client-java"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "com.akkademy-db" %% "akkademy-db-java" % "0.0.1-SNAPSHOT",
  "org.scala-lang.modules" %% "scala-java8-compat" % "0.3.0",
  "junit" % "junit" % "4.12" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test"
)
