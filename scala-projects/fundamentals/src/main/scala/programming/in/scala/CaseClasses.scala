package programming.in.scala

trait CalcTrait

case class SimpleCalc (name: String) extends CalcTrait
case class ScientificCalc (name: String) extends CalcTrait
case class EngineeringCalc (name: String) extends CalcTrait

object CaseClasses extends App {

  def getCalcType (calcType: CalcTrait): String = {
    calcType match {
      case SimpleCalc(name) => s"simple calculator: $name"
      case ScientificCalc(name) => s"scientific calculator: $name"
      case EngineeringCalc(name) => s"complex calculator: $name"
      case _ => s"No calculator Found"
    }
  }

  println(getCalcType(SimpleCalc("SiCalc")))
  println(getCalcType(ScientificCalc("EnCalc")))
  println(getCalcType(EngineeringCalc("MaCalc")))
}

/**
 * Case classes provide:
 * 1. final immutable objects. No setters, only getters
 * 2. immutable formal parameters (marked as val)
 * 3. Companion objects by default
 * 4. equals(), hashcode(), toString(), apply() and unapply() methods out of the box
 * 5. objects which are easy to use in the pattern matching
 *
 * A case class cannot be created without parameters. Uncomment line 37 and see the error
 * A case object should be created if you want to create a case class without parameters
 */

//case class AnotherCalc

case object MyHomemadeCalculator
