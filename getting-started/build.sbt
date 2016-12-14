name := """actor-labs"""

version := "1.0"

scalaVersion := "2.12.0"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.1.7",

  "com.typesafe.akka" %% "akka-actor" % "2.4.12",
  "com.typesafe.akka" %% "akka-testkit" % "2.4.12" % Test,
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)
