import scala.annotation.tailrec

object RecursiveCalls {

  def main(args: Array[String]): Unit = {
    val n = 33
    println(formatResult("abs", -n, abs))
    println(formatResult("factorial",n,factorial))
    println(s"Fibonacci numbers till $n = ${fibonacci(n)}")
    println(formatResult("summation", n, summation))
    println(s"Max value of int: ${Int.MaxValue}")
  }

  private def formatResult(name: String, n: Int, f: Int => Int) : String = {
    val message = "The %s of %d is %d";
    message.format(name, n, f(n))
  }

  def abs(n: Int): Int = if (n < 0) -n else n

  def factorial(n: Int): Int = {
    @tailrec
    def loop(n: Int, acc: Int): Int = if (n <= 0) acc
    else loop(n-1, n * acc)

    loop(n, 1)
  }


  def fibonacci(n: Int): List[Int] = {

    @tailrec
    def loop(i: Int, acc: List[Int]): List[Int] = {
      val current = acc(i)
      val prev = acc(i-1)

      // We go till n-1 as we started from 1 and not 0
      if (i < n-1 ) loop(i+1, acc.concat(List(current + prev)))
      else acc

    }

    loop(1,List(0, 1))
  }

  def summation(n: Int): Int = {
    // The Range function will consider till 32
    Range(0, n+1).sum
  }
}
