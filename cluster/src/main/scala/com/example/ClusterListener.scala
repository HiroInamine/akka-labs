package com.example

import akka.actor.{Actor, ActorLogging, Address, Props}
import akka.cluster.Cluster


class ClusterListener extends Actor with ActorLogging {

  import ClusterListener._
  import akka.cluster.ClusterEvent._

  private var members = Set.empty[Address]

  val cluster = Cluster(context.system)

  // subscribe to cluster changes, re-subscribe when restart
  override def preStart(): Unit = {
    //#subscribe
    cluster.subscribe(self, InitialStateAsEvents, classOf[MemberEvent])
    //#subscribe
  }

  override def postStop(): Unit = cluster.unsubscribe(self)

  override def receive = {
    case GetMemberNodes =>
      sender() ! members

    case MemberJoined(member) =>
      log.info("Member joined: {}", member.address)
      members += member.address

    case MemberUp(member) =>
      log.info("Member up: {}", member.address)
      members += member.address

    case MemberRemoved(member, _) =>
      log.info("Member removed: {}", member.address)
      members -= member.address
  }
}

object ClusterListener {

  case object GetMemberNodes

  final val Name = "cluster-listener"

  def props: Props = Props(new ClusterListener)
}
