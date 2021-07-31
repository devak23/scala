package challenge_01

object ScalaChallenge01 {

  def main(args: Array[String]): Unit = {
    val impl: Implementation = new Implementation()
    println(impl.sumEven(2, 8, 0))
    println(impl.sumEven(1, 9, 0))
  }
}
