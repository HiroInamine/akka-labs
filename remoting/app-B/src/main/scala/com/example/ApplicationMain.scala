package com.example

import akka.actor.{ActorSystem, Props}
import org.slf4j.LoggerFactory

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object ApplicationMain extends App {
  val system = ActorSystem("system-b")

  val logger = LoggerFactory.getLogger(this.getClass)
  logger.info("Initializing app B ...")

  val pongActor = system.actorOf(Props[PongActor], "pongActor")

  Await.result(system.whenTerminated, Duration.Inf)
}
