object Main extends App {
  def penultimate(list:List[Int]): Option[Int] = list match{
    case head::next::Nil => Some(head)
    case head::next => penultimate(next)
    case Nil => None
  }

  def length(list:List[Int]):Int = list.foldLeft(0)((c, _) => c+1)
  def flatten(list:List[List[Int]]):List[Int] = list.flatMap(x=>x)
  def duplicate (list: List[Int]):List[Int] = list.flatMap(x=>List(x,x))
  //println(penultimate(List(1,2,3)))
  //println(length(List(1,2,3,4,5)))
  //println(flatten(List(List(1,2), List(3,4))))
  println(duplicate(List(1,2,3)))
}
