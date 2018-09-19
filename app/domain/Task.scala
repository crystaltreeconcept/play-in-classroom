package domain

import scala.util.Random

case class Task(text:String, answerCheck:AnswerCheck)

object Task {

  object Int {
    def unapply(s: String): Option[Int] = util.Try(s.toInt).toOption
  }

  def random(): Task = {
    val first = Random.nextInt % 20
    val second = Random.nextInt % 20
    def predicate:NumberMatchPredicate = NumberMatchPredicate(first + second)
    val answerCheck: AnswerCheck = AnswerCheck(predicate)
    Task(s"$first + $second = ", answerCheck)
  }
}
