package programming.in.scala

object SameLength extends App {

  val geniuses = List("Newton", "Einstein", "Pascal", "Powell", "Faraday", "Currie", "Gauss", "Tesla", "Bose").sorted
  val geniusMap: Map[Int, List[String]] = geniuses.groupBy( e => e.length )

  def listToString(listOfNames: List[String]): String = listOfNames match {
    case Nil => ""
    case head::tail => head + " " + listToString(tail)
  }

  geniusMap.foreach {case(count,names) => println(s"$count: ${listToString(names)}")}
}
