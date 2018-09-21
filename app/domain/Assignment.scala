package domain

import widgets.TasksAssignment

import scala.util.Try

case class Assignment(id: AssignmentId, tasks: List[Task])

object Assignment {
  val DEFAULT_NR_TASKS = 10
  /**
    * generate new assignment for now
    * TODO: replace with actual assignment lifetime logic
    */
  def generate(nrTasks: Int):Assignment = {
    val tasks:List[Task] = (0 until nrTasks).map( _ => Task.random()).toList
    Assignment(10, tasks)
  }
  def apply():Assignment = generate(DEFAULT_NR_TASKS)
  def submit(assignment: TasksAssignment):String = {
    val totalCorrect = assignment.tasks.count(task =>
      Try(task.answerText.toInt).toOption.contains(task.answerCheckId))
    Grading.getMark(totalCorrect, assignment.tasks.size)
  }
  //TODO: perform calculation of grading/marking
  //TODO: re-factor packages constitution for assignment and widgets
}

object Grading {
  def getMark(correctAnswers:Int, totalQuestions:Int):String = {
    correctAnswers.toDouble / totalQuestions match {
      case res if res > 0.8 => "'A'"
      case res if res > 0.6 => "'B'"
      case res if res > 0.4 => "'C'"
      case res if res > 0.2 => "'D'"
      case _ => "'F'"
    }
  }
}