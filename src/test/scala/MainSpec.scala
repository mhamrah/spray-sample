package com.mlh.spraysample
import org.scalatest.FreeSpec
import org.scalatest.matchers.ShouldMatchers
import spray.testkit.ScalatestRouteTest
import spray.http.HttpEntity
import spray.http.ContentTypes
import spray.can.server.Stats

class MainSpec extends FreeSpec with ShouldMatchers with ScalatestRouteTest with SpraySampleService {
  def actorRefFactory = system

  "The spraysample Route" - {
    "when listing entities" - {
      "returns a JSON list" in {
        Get("/entity") ~> spraysampleRoute ~> check {
          assert(contentType.mediaType.isApplication)
          contentType.toString should include("application/json")
          // FIXME: Should be able to get response as JObject
          responseAs[String] should equal("\"list\"")
        }
      }
    }
    "when posting an entity" - {
      "gives a JSON response" in {
        // FIXME: Should be able to send entity as JObject
        Post("/entity", HttpEntity(ContentTypes.`application/json`, """{"json":[ ]}""")) ~> spraysampleRoute ~> check {
          responseAs[String] should include("\"I got a response: Ok")
        }
      }
    }
    "Get stats" - {
      "gives a JSON response" in {
        pending
        // FIXME: Request was neither completed nor rejected within 1
        // second. Error during processing of request
        // HttpRequest(GET,http://example.com/stats,List(),Empty,HTTP/1.1)
        // akka.pattern.AskTimeoutException: Timed out
        Get("/stats") ~> spraysampleRoute ~> check {
          responseAs[String] should include("uptime")
        }
      }
    }
  }
}


