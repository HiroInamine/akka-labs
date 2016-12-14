import NativePackagerHelper._

name := """app-A"""

version := "1.0.0"
scalaVersion := "2.11.8"

enablePlugins(JavaAppPackaging)

mainClass in Compile := Some("com.example.ApplicationMain")

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.14",
  "com.typesafe.akka" %% "akka-remote" % "2.4.14",
  "com.typesafe.akka" %% "akka-slf4j" % "2.4.14",

  "ch.qos.logback" % "logback-classic" % "1.1.7",

  "com.typesafe.akka" %% "akka-testkit" % "2.4.14" % "test",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)

mappings in Universal ++= {
  directory("") ++
    contentOf("src/main/resources").toMap.mapValues("config/" + _)
}
