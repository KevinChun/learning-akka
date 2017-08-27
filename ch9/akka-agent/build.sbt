name := "akka-agent"

version := "1.0"

scalaVersion := "2.11.6"

lazy val akkaVersion = "2.3.11"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-agent" % akkaVersion
)
