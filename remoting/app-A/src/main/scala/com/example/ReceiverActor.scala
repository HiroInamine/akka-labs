package com.example

import akka.actor.{Actor, ActorLogging}

class ReceiverActor extends Actor with ActorLogging {

  def receive = {
    case Counter(n) =>
      log.info(s"RECEIVER $n")
  }
}
