object FunctionLiteralsAndPlaceHolders extends App {
  (x: Int) => x * 100
  // Above is a function literal. A function literal gets compiled into a class that is instantiated
  // at runtime as a function object. This the definition of anonymous functions. But you just defined a
  // function object. You have no way to invoke it because you didn't assign it to any variable/handle

  val multiplyBy = (x: Int) => x * 100
  println(multiplyBy(3))
  println(multiplyBy(7))

  val readGooglesStockData = () => {
//    val source = io.Source.fromFile("src/main/scala/resources/google_stock_price_train.csv")
    val source = io.Source.fromFile("src/main/scala/resources/GOOG.csv")
    for {
      line <- source.getLines().drop(1).toVector
      cols = line.split(",").map(_.trim)
    } yield StockRecord(
      date = cols(0)
      , open = cols(1).toDouble
      , high = cols(2).toDouble
      , low = cols(3).toDouble
      , close = cols(4).toDouble
      , volume = cols(5).replace("\"","").toDouble)
  }

  var data = readGooglesStockData()

  val rows = () => data.size

  val avgCloseValue = () => data.map(_.close).sum / data.size
  val minCloseValue = () => data.map(_.low).min
  val maxCloseValue = () => data.map(_.high).max
  val closeValueOnDate = (date: String) => data.filter(_.date == date).map(_.close).head
  val maxVolume = () => data.map(_.volume).max
  val priceDelta = (_: Double) - (_: Double)
  val dailyDelta = (openPrice: Double, closePrice: Double, delta: (Double, Double) => Double) => delta(openPrice, closePrice)


  println(s"Dataset size: ${rows()}")
  println(s"Average close: ${avgCloseValue()}")
  println(s"Min close: ${minCloseValue()}")
  println(s"Max close: ${maxCloseValue()}")
  println(s"Max volume: ${maxVolume()}")

  val date = "2020-01-03"
  println(s"Close value on $date = ${closeValueOnDate(date)}")

  val recordsAtDate = data.filter(_.date == date)
  println(recordsAtDate)

  // Both these are the same. One is invoked via place holder and the other one via explicit function
  println(s"dailyDelta = ${dailyDelta(recordsAtDate(0).open, recordsAtDate(0).close, priceDelta)}")
  println(s"dailyDelta = ${dailyDelta(recordsAtDate(0).open, recordsAtDate(0).close, _ - _)}")



}
