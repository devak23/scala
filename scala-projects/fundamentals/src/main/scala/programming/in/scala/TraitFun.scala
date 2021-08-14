package programming.in.scala

// A trait is similar to an interface with default methods in Java.
// Class, case class, object and even traits themselves can extend multiple traits at the same time.
trait Calculator {
  override def toString: String = {
    s"This is a trait method that has an implementation"
  }

  def getName: String
  def fetchCost: Double
  def calcType: String
}

class ScientificCalculator (name: String, cost: Double) extends Calculator {
  override def getName: String = name

  override def fetchCost: Double = cost

  override def calcType: String = "Scientific"

  override def toString: String = s"This is the Scientific calculator"
}

class SimpleCalculator (name: String, cost: Double) extends Calculator {
  override def getName: String = name

  override def fetchCost: Double = cost

  override def calcType: String = "Simple"

  override def toString: String = s"This is a Simple calculator"
}

object TraitFun extends App {
  val casioDailyUse = new SimpleCalculator("Casio Daily Calculator", 10)
  val casioScientific = new ScientificCalculator("Casio Scientific Calculator", cost = 50)

  println(casioScientific, casioDailyUse)
}


