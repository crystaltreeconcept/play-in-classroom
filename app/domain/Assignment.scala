package domain

case class Assignment(val id: AssignmentId, val tasks: List[Task])

object Assignment {
  val DEFAULT_NR_TASKS = 10
  /**
    * generate new assignment for now
    * TODO: replace with actual assignment lifetime logic
    */
  def apply(nrTasks: Int):Assignment = {
    val tasks:List[Task] = (0 until nrTasks).map( _ => Task.random()).toList
    return Assignment(10, tasks)
  }
  def apply():Assignment = apply(DEFAULT_NR_TASKS)
}
