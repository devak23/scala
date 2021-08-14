package programming.in.scala

trait TSimpleCalculator {
  def simpleOperations = s"Simple operations"
}

trait TScientificCalculator {
  def scientificOperations = s"Scientific operations"
}

class MyCalculator

object InstantiationWithTraits extends App {

  val simpleCalc = new MyCalculator with TSimpleCalculator
  println(simpleCalc.simpleOperations)

  val scientificCalc = new MyCalculator with TScientificCalculator
  println(scientificCalc.scientificOperations)
}
