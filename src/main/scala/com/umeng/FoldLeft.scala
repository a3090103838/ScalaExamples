package com.umeng

object FoldLeft extends App{
  val nums = List(1,2,3,4,5)
  println("sum:" + nums.foldLeft(0) {(m:Int, n:Int) => m+n} )
}
