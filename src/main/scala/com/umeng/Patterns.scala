
object Patterns{
  trait Tree
  case class Branch(l:Tree,r:Tree) extends Tree
  case class Leaf(n:Int) extends Tree

  def sumAll(tree:Tree):Int = tree match{
    case Branch(l, r) => sumAll(l) + sumAll(r)
    case Leaf(n) => n
  }

  def main(args:Array[String]){
    println(sumAll( Branch(Branch(Leaf(1),Leaf(2)),Branch(Leaf(3),Leaf(4))  ) ))
  }
}
