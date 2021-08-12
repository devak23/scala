object FirstProgram {
  def main(args: Array[String]): Unit = {
    val toDouble: (Int => Int) = _* 2
    (1 to 10) foreach toDouble .andThen(println)

    val stocks = List("APPL", "GOOG", "MSFT", "TESLA")
    stocks.foreach(println)
  }
}
