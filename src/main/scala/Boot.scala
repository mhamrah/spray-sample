package com.mlh.spraysample

import scala.concurrent.duration._

import akka.actor.{Actor, ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import org.json4s._
import org.json4s.native.JsonMethods._
import org.json4s.native.Serialization.{read, write}
import spray.can.Http
import spray.httpx.Json4sSupport
import spray.routing._
import spray.util._

object Boot extends App {
  implicit val system = ActorSystem("spray-sample-system")

  /* Spray Service */
  val service= system.actorOf(Props[SpraysampleActor], "spray-sample-service")
  IO(Http) ! Http.Bind(service, system.settings.config.getString("app.interface"), system.settings.config.getInt("app.port"))

  println("Hit any key to exit.")
  val result = readLine()
  system.shutdown()
}

class SpraysampleActor extends Actor with SpraysampleService with SprayActorLogging {
  def actorRefFactory = context
  def receive = runRoute(spraysampleRoute)
}

object Json4sProtocol extends Json4sSupport {
  implicit def json4sFormats: Formats = DefaultFormats
}

trait SpraysampleService extends HttpService {
  import Json4sProtocol._
  import WorkerActor._

  implicit def executionContext = actorRefFactory.dispatcher
  implicit val timeout = Timeout(5 seconds)


  val worker = actorRefFactory.actorOf(Props[WorkerActor], "worker")

  val spraysampleRoute = {
    path("entity") {
      get { 
        complete("list")
      } ~
      post {
        entity(as[JObject]) { asset =>
          doCreate(asset)
        }
      } ~
      path ("entity" / Segment) { id =>
        get {
          complete("detail")
        } ~
        post {
          complete("update")
        }
      }
    }
  }

  def doCreate[T](json: JObject) = {
    val result = (worker ? Create(json))
                  .mapTo[Ok]
                  .map(result => result)
                  .recover { case _ => "error" }

    complete(result)
  }

}
