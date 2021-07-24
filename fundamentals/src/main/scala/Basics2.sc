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
// newAbhay.age = 43
// We will need to create a new instance by using copy
val aDifferentAbhay = newAbhay.copy(name = "Abhay", age = 43)
val areTheySameNow = aDifferentAbhay.equals(newAbhay)


case class City (name: String, urbanArea: Int)

// Defining a companion object for City which defines some constants. A companion object
// must be defined in the same file as the class using the keyword "object" followed by the
// name of the class its accompanying. A companion object is always a singleton. It has
// its own type and is not an instance of the accompanied class. This object defines
// static functions or values that are closely related to the class its accompanying. In
// other words, all static members are defined within the companion object.
object City {
  val London = City("London", 1738)
  val Lausanne = City("Laussane", 153)
  val Mumbai = City("Mumbai", 72)
}

case class APerson (firstName: String, lastName: String, city: City)

// Defining a companion object for APerson. Some functions in companion object have
// special meaning. Functions named 'apply' are constructors of the class. The name
// 'apply' can be omitted when we call them.
object APerson {
  def apply (fullName: String, city: City): APerson = {
    val split = fullName.split(" ")
    new APerson(split(0), split(1), city)
  }
}
val p1 = APerson ("Abhay", "Kulkarni", City.Mumbai)
val p2 = APerson ("Abhay Kulkarni", City.Mumbai)
val p3 = APerson.apply("Abhay Kulkarni", City.Mumbai)

p1 == p2
p2 == p3
p1 == p3