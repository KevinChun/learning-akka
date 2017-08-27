name := "futures-examples-java"

version := "1.0"

scalaVersion := "2.12.2"

lazy val akkaVersion = "2.5.4"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "org.scala-lang.modules" %% "scala-java8-compat" % "0.8.0",
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
  "junit" % "junit" % "4.12" % "test",
  "com.novocode"  % "junit-interface" % "0.11" % "test"
)
