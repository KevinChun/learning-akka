name := "akkademaid-scala"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.0.9",
  "com.akkademy-db" %% "akkademy-db-scala" % "0.0.1-SNAPSHOT",
  "com.syncthemall" % "boilerpipe" % "1.2.2",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.4" % "test"
)
