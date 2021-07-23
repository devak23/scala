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

// the class "mixes in" the traits Coordinates, Description and Area. Note that we need to
// use the keyword extends before trait or class and keyword with for subsequent traits
// Notice how the TraitRectangle's x and y coordinates are "tied up" to the Coordinates
// trait. Change the x and y in rectangle to say 'a' and 'b' and compiler flags an error
// saying you haven't implemented the Coordinates Trait.
class TraitRectangle (val x: Int, val y: Int, val width: Int ,val height: Int)
  extends Coordinates with Description with Area {
  override val area: Double = BigDecimal(width * height).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble

  override def description: String =
    s"A Rectangle of $width units by $height units situated at " + super.description + s" has an area of $area sq. units."
}

val rect = new TraitRectangle(x = 0, y = 3, width = 4, height = 5)
rect.description

// Another interesting point is we can implement an abstract method with "val". In trait
// Area, we defined the area using "def" and implemented in the TraitRectangle using "val"
// It is a good practise to define the abstract methods with def and leave the implementer
// to decide if the implementation requires a method (def) or a variable (val)


case class Person (name: String, age: Int)

val abhay = new Person (name = "abhay", age = 42)
val newAbhay = new Person(name = "abhay", age = 42)
newAbhay == abhay
newAbhay.eq(abhay) // both the references are different
newAbhay.equals(abhay) // but the object is the same.

// The following line does NOT compile because a case class is immutable
//newAbhay.age = 43
// We will need to create a new instance by using copy
val aDifferentAbhay = newAbhay.copy(name = "Abhay", age = 43)
val areTheySameNow = aDifferentAbhay.equals(newAbhay)
