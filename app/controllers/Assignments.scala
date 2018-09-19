package controllers

import domain._
import javax.inject._
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc._


/**
  * REST controller for user assignments
  *
  *
  * @param cc
  */
@Singleton
class Assignments @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def get(userId: UserId, assignmentId: AssignmentId) = Action { implicit request: Request[AnyContent] => {

      implicit val taskClientHandleWrites = new Writes[Task] {

        def encode(answerCheck: AnswerCheck): Json.JsValueWrapper = answerCheck.predicate match {
          case NumberMatchPredicate(number) => Json.obj ("match" -> number.toString)
          case _ => Json.obj ("undefined" -> "" )
        }

        def writes(task: Task) = Json.obj(
          "text" -> task.text,
          "answerCheckId" -> encode(task.answerCheck)
        )
      }

      implicit val assignmentClientHandleWrites = new Writes[Assignment] {
        def writes(assignment: Assignment) = Json.obj(
          "id" -> assignment.id,
          "tasks" -> assignment.tasks
        )
      }

      Ok(Json.toJson(Assignment()))
    }
  }

  /*
  TODO: use play forms api here
  https://www.playframework.com/documentation/2.6.x/ScalaForms
   */

  def post(userId: UserId, assignmentId: AssignmentId) = Action { implicit request: Request[AnyContent] => {
    val answers:Option[JsValue] = request.body.asJson
    Ok("Yeah, ok, fine...")
  }
  }
}
