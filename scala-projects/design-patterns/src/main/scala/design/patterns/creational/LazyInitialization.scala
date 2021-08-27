package design.patterns.creational

import java.util.Properties

object CircleUtils {
  val basicPi = 3.14

  // Lazily initialize the PI value from the property file
  lazy val precisePi: Double = {
    println("Reading the value of pi from properties. -- You should see this only once and only when the area with precise value is invoked.")
    val props = new Properties()
    props.load(getClass.getResourceAsStream("/precisepi.properties"))
    props.getProperty("precise.pi").toDouble
  }

  def area(radius: Double, isPrecise: Boolean): Double = {
    val pi = if (isPrecise) precisePi else basicPi;
    pi * Math.pow(radius, 2)
  }
}

object LazyInitialization extends App {
  println(s"Basic area for a circle with radius 2.5 = ${CircleUtils.area(2.5, false)} sqft.")
  println(s"Precise area for a circle with radius 2.5 = ${CircleUtils.area(2.5, true)} sqft.")
  println(s"Basic area for a circle with radius 6.78 = ${CircleUtils.area(6.8, false)} sqft.")
  println(s"Precise area for a circle with radius 6.78 = ${CircleUtils.area(6.8, true)} sqft.")
}
