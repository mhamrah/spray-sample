package com.mlh.spraysample
import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http.HttpEntity
import spray.http.ContentTypes

class MainSpec extends Specification with Specs2RouteTest with SpraySampleService {
  def actorRefFactory = system

  "The spraysample class" should {
    "List entities" in {
      Get("/entity") ~> spraysampleRoute ~> check {
        contentType.mediaType.isApplication must beEqualTo(true)
        contentType.toString must contain("application/json")
	// FIXME: Should be able to get response as JObject
	responseAs[String] === "\"list\""
      }
    }
    "Post an entity" in {
      // FIXME: Should be able to send entity as JObject
      Post("/entity", HttpEntity(ContentTypes.`application/json`, """{"json":[ ]}""")) ~> spraysampleRoute ~> check {
	responseAs[String] must contain("\"I got a response: Ok")
      }
    }
    "Get stats" in {
      pending
    }
  }
}


