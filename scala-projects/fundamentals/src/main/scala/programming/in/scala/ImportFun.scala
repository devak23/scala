package programming.in.scala

// Scala allows rename during import
import math.{Pi => PI}
import math.{pow => power}

object ImportFun extends App {

  def areaOfCircle(rad: Float): Float = (PI * power(rad, 2)).toFloat

  val radius: Float = 7.0F
  println(s"area of a circle with radius: $radius = ${areaOfCircle(radius)}")
}
