package com.example.app

import akka.actor.{Actor, ActorRef, ActorSystem}
import akka.pattern.ask
import akka.util.Timeout
import org.scalatra._

import scala.concurrent.ExecutionContext
import scala.language.postfixOps
import scala.concurrent.duration._

class MyScalatraServlet(system: ActorSystem, myActor: ActorRef) extends ScalatraServlet with FutureSupport{

  override protected implicit def executor: ExecutionContext = system.dispatcher
  implicit val timeout: Timeout = new Timeout(2 seconds)

  get("/") {
    myActor ? "hello"
  }

  notFound {
    resourceNotFound()
  }
}

class MyActor extends Actor {
  def receive = {
    case _ => sender ! "Hello World"
  }
}
