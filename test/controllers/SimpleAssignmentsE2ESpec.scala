package controllers

import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.concurrent.Eventually
import org.scalatest.mockito.MockitoSugar
import org.scalatest.selenium.WebBrowser
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class SimpleAssignmentsE2ESpec
  extends PlaySpec
    with GuiceOneAppPerTest
    with Injecting
    with MockitoSugar
    with WebBrowser
    with Eventually
{

  implicit val webDriver: WebDriver = new HtmlUnitDriver
  val host = "http://localhost:9000/"

  "Assignments Workflow" should {

    "correctly process scenario for solve the assignment, happy path 1" in {

      go to (host + "quiz")

      eventually { pageTitle must be ("Assignment") }

      find(tagName("h1")).map( elem => elem.text ) must be (Some ("Please solve this assignment:"))

    }

    "show quiz page from '/'" in {

      go to (host + "")

      eventually { currentUrl must be (host + "quiz") }

    }

    /*
    this is a very bad test, because it's
     */
    "can process answers, happy path 1" in {

      go to (host + "quiz")

      /*
      click and enter each answer with 1
      */
      val inputs:List[Element] = findAll(tagName("input")).toList
      inputs
        .filter( in1 => in1.isDisplayed )
        .map( in2 => in2.attribute("id") )
        .flatten
        .map(textField)
        .foreach( inputField => {
          click on inputField
          inputField.value = "1"
        })
      submit()

      eventually { pageSource must be ("You are getting 'F' for this task") }

    }

    /*
    don't fill in the answers, just submit empty quiz, should get 'F'
     */
    "no answers, getting 'F'" in {

      go to (host + "quiz")

      /*
      * click on first of the inputs, so the submit will make sense in terms of form focus
      */
      val inputs:List[Element] = findAll(tagName("input")).toList
      inputs
        .filter( in1 => in1.isDisplayed )
        .map( in2 => in2.attribute("id") )
        .flatten
        .map(textField)
        .collectFirst {
          case inputField => click on inputField
        }

      submit()

      eventually { pageSource must be ("You are getting 'A' for this task") }

    }

  }
}
