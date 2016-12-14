package com.example

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object ApplicationMain extends App {
  val system = ActorSystem("system-a")

  val logger = LoggerFactory.getLogger(this.getClass)
  logger.info("Initializing app A ...")
  //  logger.info(s"${system.settings.config.getConfig("akka.remote")}")


  /**
    * Sends a remote message from another actor.
    */
  def sendMessageFromActor() = {
    val pongPath = ConfigFactory.load().getString("system-b.pongPath")
    val pongSel = system.actorSelection(pongPath)
    val pingActor = system.actorOf(
      Props(classOf[PingActor], pongSel), "pingActor")

    system.scheduler.schedule(0.seconds, 5.seconds) {
      pingActor ! Counter(5)
    }
  }

  /**
    * Sends a remote message from root actor.
    */
  def sendMessageFromRoot() = {
    val pongPath = ConfigFactory.load().getString("system-b.pongPath")
    val pongSel = system.actorSelection(pongPath)
    val recActor = system.actorOf(Props[ReceiverActor], "recActor")

    system.scheduler.schedule(0.seconds, 5.seconds) {
      // System b - DeadLetters
//      pongSel ! Counter(1)

      // System b - send response to refActor
//      pongSel.tell(Counter(1), recActor)

      // OK - receives FUTURE from system B
      implicit val timeout = Timeout(5.seconds)
      val future = pongSel ? Counter(5)
      future.map(v => logger.error(s"$v"))
    }
  }

   sendMessageFromActor()
//  sendMessageFromRoot()

  Await.result(system.whenTerminated, Duration.Inf)
}
