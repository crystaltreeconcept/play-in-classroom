package controllers

import domain._
import javax.inject._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.JsValue
import play.api.mvc._
import widgets.{AnsweredUserTask, TasksAssignment}


/**
  * REST controller for user assignments
  *
  */
@Singleton
class Assignments @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {
  /*
  Form:
    answers -> Seq[UserAnswer](userText:String, checkUuid:uuid)
   */

//  implicit object UrlFormatter extends Formatter[UserAnswers] {
//    override val format = Some(("format.url", Nil))
//    override def bind(key: String, data: Map[String, String]) = parsing(new URL(_), "error.url", Nil)(key, data)
//    override def unbind(key: String, value: URL) = Map(key -> value.toString)
//  }

  val userAnswersForm = Form(
    mapping(
      "answers" -> seq(
        mapping(
          "taskText" -> text,   //text
          "answerText" -> text,
          "answerCheckId" -> longNumber   //TODO: replace with UUID
        )(AnsweredUserTask.apply)(AnsweredUserTask.unapply)
      ),
    )(TasksAssignment.apply)(TasksAssignment.unapply)
  )

  /**
    * render quiz page
    * @return
    */
  def generateUserAssignment = Action { implicit request =>
    val preFilledAssignment = TasksAssignment(
      tasks = Assignment.generate(10).tasks.map( task =>
            AnsweredUserTask(
              task.text,
              "",
              task.answerCheck.predicate match {  //TODO: replace with temporary server-only generated answer ID check
                case NumberMatchPredicate(intAnswer) => intAnswer.toLong
              }
            )
      )
    )
    Ok(views.html.userAnswers(userAnswersForm.fill(preFilledAssignment)))
  }

  def solveAssignment = Action { implicit request =>
    userAnswersForm.bindFromRequest.fold(
      formWithErrors => {
        Ok("Will need to render results processing with BadRequest(views.html.contact.form(formWithErrors))")
      },
      tasksAssignment => {
        val mark = Assignment.submit(tasksAssignment)
        Ok(s"You are getting $mark for this task")
      }
    )
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
