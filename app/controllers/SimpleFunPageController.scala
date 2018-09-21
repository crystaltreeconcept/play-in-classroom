package controllers

import javax.inject._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class SimpleFunPageController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Renders single random page of K-Grade Math exercises
    *
    *
    * Page contains:
    *   hello-visuals
    *   form of exercises (w/ submit button)
    *     each exercise has the answer encoded
    *     TBD: make 'weaklink' style of collections for an answers of the potentially pending generated tasks, per user
    *
   */
//  def index() = Action { implicit request: play.api.mvc.RequestHeader =>
//    Redirect(routes.SimpleFunPageController.funTasksWelcome())
//  }

//  def funTasksWelcome() = Action { implicit request: play.api.mvc.RequestHeader =>
//    Ok(views.html.funTasksWelcome("Solve this assignment"))
//  }
}
