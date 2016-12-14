package com.example

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, _}

object ApplicationMain extends App {
  val logger = LoggerFactory.getLogger(this.getClass)
  logger.info("Initializing app ...")

  val config = args.toList match {
    case myList@port :: Nil =>
      println(port)
      ConfigFactory.parseString("akka.remote.netty.tcp.port=" + port).
        withFallback(ConfigFactory.load())

    case _ =>
      println("default args")
      ConfigFactory.load()
  }

  val system = ActorSystem("cluster-lab", config)

  //  val pongActor = system.actorOf(Props[PongActor], "pongActor")
  //
  //  implicit val timeout = akka.util.Timeout(10.seconds)
  //  val future = pongActor ? Counter(10)
  //  future.mapTo[Counter].map {
  //    number =>
  //      println(number)
  //  }
  //
  //  Await.result(system.whenTerminated, Duration.Inf)
}
