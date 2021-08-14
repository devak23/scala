package programming.in.scala

trait Shape {
  override def toString: String = s"This has a shape of ${getShapeDescription}"
  def getShapeDescription: String
}

trait Volume {
  override def toString: String = s"This has a volume of ${getVolume} units"
  def getVolume: Double
}

class Sphere (radius: Double) extends Volume with Shape {
  override def getShapeDescription: String = "a sphere"

  override def getVolume: Double = math.Pi * math.pow(radius, 3)
}

object TraitInheritanceCheck extends App {
  val earth: Sphere = new Sphere(7)
  println(earth) // What should be printed? toString from Shape or toString from Volume ?
}

/**
 * The Shape's toString gets printed in this case due to a process called "Linearization". The output in this particular
 * case, depends on how the traits have been arranged by the compiler. The multiple inheritance ordering from lowest
 * subclass to highest base class is right to left. Therefore, Sphere extending Volume with Shape causes the compiler
 * implementation as "Sphere extends Shape extends Volume". Thus the Shape trait (rightmost trait) becomes the immediate
 * parent of the class being defined.
 */
