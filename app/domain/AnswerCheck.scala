package domain

import scala.util.{Success, Try}

case class NumberMatchPredicate(number:Int) {
  def matches(that:String):Boolean = Try( that.toInt ) == Success( number )
}

object NumberMatchPredicate {
  def apply(on:Int):NumberMatchPredicate = new NumberMatchPredicate(on)
  def unapply(arg: NumberMatchPredicate): Option[Int] = Some(arg.number)
}

case class AnswerCheck(predicate: NumberMatchPredicate)
