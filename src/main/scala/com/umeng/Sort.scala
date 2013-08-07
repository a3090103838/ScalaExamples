
object Sort{

  def sort(nums:List[Int]): List[Int] = {
    if(nums.length < 2) 
      nums
    else{
      val pivot = nums(nums.length/2)
      def lt(a:Int) = a < pivot
      def eq(a:Int) = a == pivot
      def gt(a:Int) = a > pivot
      sort(nums filter lt) :::
         (nums filter eq) :::
         sort(nums filter gt)
  }
}

  def main(args:Array[String]){
    val nums = List(5,4,8,1,3)
    println(nums)
    println(sort(nums))
  }
}
