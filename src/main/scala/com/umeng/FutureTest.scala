package com.umeng

import com.twitter.util.Future
import scala.util.Random

object FutureTest extends App {

  // not too exciting, the result will always be 42. but more importantly, when?
  println("1 - starting calculation ...")
  val f = Future {
    sleep(Random.nextInt(500))
    42
  }

  println("2- before onComplete")
  f onSuccess  {ans =>
    println("ans:"+ ans)
  }

  // do the rest of your work
  println("A ..."); sleep(100)
  println("B ..."); sleep(100)
  println("C ..."); sleep(100)
  println("D ..."); sleep(100)
  println("E ..."); sleep(100)
  println("F ..."); sleep(100)

  // keep the jvm alive (may be needed depending on how you run the example)
  //sleep(2000)

  def sleep(duration: Long) { Thread.sleep(duration) }

}