name := "akkademy-db"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.12.2"

lazy val akkaVersion = "2.5.4"

libraryDependencies ++= Seq(
  "com.akkademy-db" %% "akkademy-db-scala" % "0.0.1-SNAPSHOT",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)
