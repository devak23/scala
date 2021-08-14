package programming.in.scala

object WordCount extends App {

  val words = List("Hello", "Hi", "How", "are", "you", "Hi", "again", "Hope", "you", "are", "good", "Hi", "there!")
  val countOfWords: Map[String, Int] = words.groupBy(identity).map{case (k,v) => (k, v.length)}
  //println(words.groupBy(identity))
  countOfWords.foreach{ case(k,v) => println(s"word $k occurs $v times")}
}
