package programming.in.scala

object CalculatorApplyUnApply extends App {

  // The apply method creates a CalculatorApplyUnApply string object from the name and calcType as shown below. The
  // apply is like a constructor that takes in arguments and creates an object.
  def apply(name: String, calcType: String): String = s"${calcType}--${name}"

  // The unapply (case is important. unApply -wont work) method does the reverse. It takes in an object and gives back
  // the argument. Here the unapply takes a String (remember apply method returned a string) and gives back the type.
  def unapply(calc: String): Option[String] = {
    val array: Array[String] = calc.split("--")
    if (array.tail.nonEmpty) Some (array.head) else None
  }

  // So when we call the function below, this is a shorthand syntax of calling CalculatorApplyUnApply.apply("casio", "simple")
  val calc = CalculatorApplyUnApply("casio", "simple")

  // Thus the calc value now is "simple--casio". In the case statement below, we take this string in the match expression
  // and when we invoke CalculatorApplyUnApply("simple-casio"), the unapply method gets invoked and hence the output
  // - simple
  calc match {
    case CalculatorApplyUnApply(calcType) => println(calcType)
    case _ => println("Couldn't extract Calculator type")
  }

  // In scala, when an object has an apply and unapply methods defined, it falls into a special category of objects called
  // as the Extractor Object
}
