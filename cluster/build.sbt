import com.typesafe.sbt.packager.MappingsHelper._

name := """cluster"""

version := "1.0.0"
scalaVersion := "2.11.8"

enablePlugins(JavaAppPackaging)

mainClass in Compile := Some("com.example.ApplicationMain")

val akkaVersion = "2.4.14"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,

  "ch.qos.logback" % "logback-classic" % "1.1.7",

  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)


mappings in Universal ++= {
  directory("") ++
    contentOf("src/main/resources").toMap.mapValues("config/" + _)
}
