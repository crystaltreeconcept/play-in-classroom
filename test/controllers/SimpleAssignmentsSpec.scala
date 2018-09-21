package controllers

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.libs.json.Json
import play.api.mvc.RequestHeader
import play.api.test.CSRFTokenHelper._
import play.api.test.Helpers._
import play.api.test._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class SimpleAssignmentsSpec extends PlaySpec with GuiceOneAppPerTest with Injecting with MockitoSugar {

  implicit val sys: ActorSystem = ActorSystem("MyTest")
  implicit val mat: ActorMaterializer = ActorMaterializer()


  "Assignments Controller GET" should {

    "render the index page from a new instance of controller" in {
      val controller = new Assignments(stubControllerComponents())
      val request:RequestHeader = FakeRequest().withCSRFToken
      val result = controller.generateUserAssignment().apply(request)

      status(result) mustBe OK
      contentType(result) mustBe Some("text/html")
      contentAsString(result) must include ("<button type=\"submit\">Done!</button>")
      contentAsString(result) must include ("Type in the result of")
      contentAsString(result) must include ("<input type=\"text\" id=\"answers")
    }


    "submit answers should result in some marking grade message" in {
      val controller = new Assignments(stubControllerComponents())

      val request = FakeRequest(POST, "/")
        .withJsonBody(Json.parse(
          """{ "answers[0].answerText": "1"
            |, "answers[0].taskText": "disregard"
            |, "answers[0].answerCheckId": "1" }""".stripMargin))
        .withCSRFToken

      val result = controller.solveAssignment().apply(request)

      status(result) mustBe OK
      contentType(result) mustBe Some("text/plain")
      contentAsString(result) must include ("You are getting 'A' for this task")
    }

  }
}
