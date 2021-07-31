package challenge_01

import scala.annotation.tailrec

class Implementation extends Abstract {
  def sum(x: Int, y: Int): Int = x + y
  def difference(x: Int, y: Int): Int = if (x == y) 0 else if (x > y) x - y else y - x
  def product(x: Int, y: Int): Int = x * y
  def isEven(x: Int): Boolean = x %2 == 0
  def roundToEven(a: Int, b: Int): (Int, Int) = (if (isEven(a)) a else a + 1, if (isEven(b)) b else b - 1)

  @tailrec
  final def sumEven (a: Int, b: Int, partialSum: Int): Int = {
    val (x, y) = roundToEven(a, b)
    if (x <= y) sumEven(x+1, y, partialSum + x) else partialSum
  }
}
