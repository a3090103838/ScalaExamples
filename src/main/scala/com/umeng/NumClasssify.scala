package com.umeng

object NumClassify extends App{

  val length = readInt
  val nums = new Array[Int](length)
  for(i <- 1 to length){
    nums(i-1) = readInt
  }

  println( "A1:"+nums.filter(_%10==0).foldLeft(0) {(m:Int,n:Int) => m+n} )
  println( "A3:"+nums.filter(_%5==2).length )
  println( "A4:"+nums.filter(_%5==3).sum.toDouble/nums.filter(_%5==3).length)
  println( "A5:"+nums.filter(_%5==4).max )
}
