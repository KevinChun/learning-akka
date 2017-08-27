name := "akkademy-db"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.12.2"

lazy val akkaVersion = "2.5.4"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "junit" % "junit" % "4.12"  % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test"
)
