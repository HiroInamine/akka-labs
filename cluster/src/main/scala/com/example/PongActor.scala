package com.example

import akka.actor.{Actor, ActorLogging}

class PongActor extends Actor with ActorLogging {
  def receive = {
    case Counter(n) if n > 0 =>
      log.info(s"PONG $n")
      if (n == 5) throw new IndexOutOfBoundsException("my exception !!!")
      sender ! Counter(n - 1)
  }
}
