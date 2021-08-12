package learning.scala.programming

import scala.annotation.tailrec

object RecursionEx extends App {

  /* 2 to power n. Works only for positive integers! Not a tail recursive call*/
  def power2ToN(n: Int): Int = if (n == 0) 1 else 2 * power2ToN(n - 1)

  println(s"2 raise to 3 = ${power2ToN(3)}")
  println(s"2 raise to 4 = ${power2ToN(4)}")
  println(s"2 raise to 16 = ${power2ToN(31)}")

  def tcPower2ToN(n: Int): Int = {
    @tailrec
    def doRecurse(n: Int,  currentValue: Int): Int = {
      if (n == 0) currentValue else doRecurse(n - 1, currentValue * 2)
    }
    doRecurse(n,1)
  }

  println(s"2 raise to 3 = ${tcPower2ToN(3)}")
  println(s"2 raise to 4 = ${tcPower2ToN(4)}")
  println(s"2 raise to 16 = ${tcPower2ToN(31)}")

}
