package controllers

import javax.inject.{Inject, _}
import play.api.mvc._
import play.api.routing._

@Singleton
class Application @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def javascriptRoutes = Action { implicit request =>
    Ok(
      JavaScriptReverseRouter("jsRoutes")(
        routes.javascript.Assignments.solveAssignment,
      )
    ).as("text/javascript")
  }

}
