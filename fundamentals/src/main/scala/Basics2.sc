// A trait is similar to an abstract class. It can define several abstract or
// concrete members and can be extended. It cannot be instantiated. A class can
// be extended by just one class, however it can "mixin" one to many traits. A
// trait cannot have constructor arguments.

trait Description {
  def description: String
}

trait Coordinates extends Description {
  def x: Int
  def y: Int
  def description: String = s"Coordinates($x, $y)"
}

trait Area {
  def area: Double
}

class TraitRectangle (val x: Int, val y: Int, val width: Int ,val height: Int)
  extends Coordinates with Description with Area {
  override def area = BigDecimal(width * height).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble

  override def description: String = s"A Rectangle of $width units by $height units situated at " + super.description
}

val rect = new TraitRectangle(0,3,4,5)
rect.description