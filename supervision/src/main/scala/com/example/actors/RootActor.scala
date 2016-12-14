package com.example.actors

import akka.actor.{Actor, ActorLogging, Props}

class RootActor extends Actor with ActorLogging {

  override def preStart(): Unit = {
    super.preStart()
    log.info("Pre tarting ...")
  }

  override def receive = {
    case message: String =>
      log.info(s"received $message")
    case any =>
      log.info(s"unhandled $any")
  }
}

/**
  * Companion object for [[RootActor]]
  */
object RootActor {
  def props() = Props[RootActor]
}
