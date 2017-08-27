name := "akkademaid-java"

version := "0.1"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-java8-compat" % "0.8.0",
  "com.akkademy-db" %% "akkademy-db-java" % "0.0.1-SNAPSHOT",
  "com.typesafe.akka" %% "akka-http" % "10.0.9",
  "com.syncthemall" % "boilerpipe" % "1.2.2",
  "junit" % "junit" % "4.12" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.4" % "test"
)
