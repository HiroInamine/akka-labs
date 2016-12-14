package com.example

import akka.actor.ActorSystem
import com.example.actors.RootActor
import org.slf4j.LoggerFactory

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, _}

object ApplicationMain extends App {
  val system = ActorSystem("mySuper")

  val logger = LoggerFactory.getLogger(this.getClass)
  logger.info("Initializing application ...")

  val rootActor = system.actorOf(RootActor.props(), "root")

  system.scheduler.schedule(0.seconds, 15.seconds, rootActor, "hi")


  Await.result(system.whenTerminated, Duration.Inf)
}
