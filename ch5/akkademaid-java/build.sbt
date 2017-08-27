name := "akkademaid-java"

version := "1.0"

scalaVersion := "2.12.2"

lazy val akkaVersion = "2.5.4"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
  "org.scala-lang.modules" %% "scala-java8-compat" % "0.8.0",
  "com.typesafe.akka" %% "akka-http" % "10.0.9",
  "junit" % "junit" % "4.12",
  "com.jason-goodwin" % "better-monads" % "0.2.0",
  "com.syncthemall" % "boilerpipe" % "1.2.2",
  "com.novocode" % "junit-interface" % "0.11" % "test"
)
