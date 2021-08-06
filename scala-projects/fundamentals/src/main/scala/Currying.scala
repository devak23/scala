object Currying extends App {

  val readFinancialData = () => {
    val source = io.Source.fromFile("src/main/scala/resources/stockMarketData.csv")
    for {
      line <- source.getLines().drop(1).toVector
      cols = line.split(",").map(_.trim)
    } yield StockRow (
      date = cols(0)
      , open = cols(1).toDouble
      , high = cols(2).toDouble
      , low = cols(3).toDouble
      , close = cols(4).toDouble
      , company = cols(5)
    )
  }

  val records = readFinancialData()

  def getPrice(date: String, ticker: String, priceType: String): Double = {
    val recordsByDateByTicker = records.filter(_.date == date).filter(_.company == ticker)

    val price = priceType match {
      case "open" => recordsByDateByTicker(0).open
      case "close" => recordsByDateByTicker(0).close
      case "high" => recordsByDateByTicker(0).high
      case "low" => recordsByDateByTicker(0).low
    }
    price
  }

  println(getPrice("12-06-2020","TM", "open"))
}
