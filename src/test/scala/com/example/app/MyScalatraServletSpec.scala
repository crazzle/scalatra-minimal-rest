package com.example.app

import akka.actor.{Props, ActorSystem}
import org.scalatra.test.specs2._

class MyScalatraServletSpec() extends ScalatraSpec {
  val system = ActorSystem()
  val actorRef = system.actorOf(Props[MyActor])

  addServlet(new MyScalatraServlet(system, actorRef), "/*")

  def is =
    "GET / on MyScalatraServlet" ^
      "should return status 200" ! root200 ^ end

  def root200 = get("/") {
    status must_== 200
  }

  def cleanUp() = {
    system.shutdown()
  }

  // cleanup there
  step(cleanUp())
}
