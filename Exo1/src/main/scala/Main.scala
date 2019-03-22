

object Main extends App{

  def last1(list: List[Int] ): Int = list match{
    case head::Nil => head
    case head::next => last1(next)
  }
  def last2(list: List[Int]):Int = list.last

  def nth1(index: Int, list: List[Int]):Int = list match{
    case head::next => if(index == 0)head else nth1(index-1, head::next)
    case Nil => -1
  }
  def nth2(index: Int, list: List[Int]):Int = list(index)

  def reverse1[Int](list:List[Int]):List[Int] = list match{
    case head::next => reverse1(next) ::: List(head)
    case Nil => Nil
  }
  def reverse2(list: List[Int]):List[Int] = list.reverse

  def compress1(list: List[Symbol]): List[Symbol] = list match{
    case head::Nil => List(head)
    case head::next => if(head == next(0))compress1(next) else List(head):::compress1(next)
  }

  def compress2(list: List[Symbol]):List[Symbol] = list.foldLeft(List[Symbol]()){
    (l, i) => if(l.contains((i))) l else l:+i
  }
  //println(nth1(0,List(0)))
  println(compress2(List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'a)))

}
