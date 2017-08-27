name := "akkademy-db-client-scala"

version := "1.0"

scalaVersion := "2.12.2"

lazy val akkaVersion = "2.5.4"

libraryDependencies ++= Seq(
  "com.akkademy-db"   %% "akkademy-db-scala" % "0.1.0-SNAPSHOT",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test"
)
