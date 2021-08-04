object VarValLazyValDef extends App {

  var data = {
    println ("Array is getting initialized")
    Array("MSFT", "GOOG", "APPL")
  }

  println("Before accessing data")
  data.foreach(println)
  println("--------------------")
  data = {println("New Array is getting initialized"); Array("IBM")}
  data.foreach(println)

  val companies = {println("Creating an array of companies"); Array("GOOG", "MSFT")}
  println("Before accessing companies")
  companies.foreach(println)
  println("--------------------")
  companies(0) = "TSLA"
  companies(1) = "APPL"
  companies.foreach(println)


  def stockPrice: Int = {
    println("Returning a stockPrice")
    14
  }
  def volume: Int = {
    println("Returning Volume purchased")
    100
  }

  println("***************")
  //def finalValue: String = stockPrice * volume
  def finalValue: String = (stockPrice * volume).toString
  println(s"The final value is: $finalValue")

  println("-----------------------------------")
  println("PLaying with Lazy val")
  println("-----------------------------------")
  var googStockPrice = 42
  val myVolume = {println("Assigning value to myVolume"); 100}
  println("calculating the final value")
  val myFinalValue = googStockPrice * myVolume
  println(s"Final value = $myFinalValue")

  println("-  -   -   -   -")
  // Now lets change the myVolume to lazy val
  lazy val myLazyVolume = {println("Assigning the value quite lazily to myLazyVolume"); 100}
  println("Now calculating the lazy final value")
  val myFinalLazyValue = googStockPrice * myLazyVolume
  println(s"Final lazy Value = $myFinalLazyValue")

  println("-----------------------------------")
  println("Functions Vs Methods")
  println("-----------------------------------")

  val tickers = Array("GOOG", "PS", "MSFT", "AAPL", "TSLA")
  def getNumberOfRowsMethod: Int = {println("Invoking method"); tickers.length}
  val getNumberOfRowsFunc = () => {println("invoking function");tickers.length}

  println(s"# of rows = $getNumberOfRowsMethod for method and ${getNumberOfRowsFunc()} for functions")
  println(getNumberOfRowsMethod.getClass)
  println(getNumberOfRowsFunc.getClass) // This is a reference to an object. Its an implementation
  // of the class which implements Traits.As you can see from the output its an anonymous method or
  // a lambda - a method with no name assigned to a val type. So how does the function invocation work?
  // It so turns out that when you invoke a function, you are actually invoking a special method called
  // "apply". A function object is an instance of a class that implements Traits that contains the
  // apply method.
  println(s"getNumerOfRowsFunc = ${getNumberOfRowsFunc.apply()}")

  // Every function object is an instance of a class that implements Traits. The traits that are
  // implemented by a function depend on the number of input arguments to the function.
  println(getNumberOfRowsFunc.isInstanceOf[Function0[_]]) // true -->

  def existsMethod(ticker: String): Boolean = tickers.contains(ticker)
  val existsFunc = (ticker: String) => tickers.contains(ticker)

  // coerce a method to a function
  val exists = existsMethod _

  println(s"""existsMethod: ${existsMethod("ATT")}""")
  println(s"""existsFunc: ${existsFunc("ATT")}""")
  println(s"""existsFunc: ${existsFunc("GOOG")}""")
}
