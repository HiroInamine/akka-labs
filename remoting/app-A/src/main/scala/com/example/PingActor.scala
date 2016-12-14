package com.example

import akka.actor.{Actor, ActorLogging, ActorSelection}

class PingActor(pongActor: ActorSelection) extends Actor with ActorLogging {

  def receive = {
    case Counter(n) =>
      log.info(s"PING $n")
      pongActor ! Counter(n)
  }
}
