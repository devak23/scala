/**
 * A function is said to have a side effect if it modifies some state or has some action
 * in the outside world. For instance, printing a string to the console, writing to a file,
 * and modifying a var are all side effects. In Scala, all expressions have type.
 * A statement which performs a side effect is of type Unit. The only value provided by Unit
 * is ()
 */
val x = println("hello") // side effect
def printName(name: String): Unit = println(name)
val y = {
  var a = 1
  a = a + 1
}
val z = ()
def helloWorld(): Unit = println("Hello World!")
helloWorld()

def helloWorldPure: String = "Hello World"
val x = helloWorldPure
x

// If else expressions
def agePeriod(age: Int) : String = {
  if (age >= 65) {
    "elderly"
  } else if (age >= 40 && age < 65) {
    "middle aged"
  } else if (age >= 18 && age < 40) {
    "young"
  } else {
    "child"
  }
}

val x = agePeriod(age = 55)

// if a sub expression has different width, the compiler will infer a common super type
// or widen the type if its a numeric
val ifElseWiden = if (true) 2: Int else 2.0: Double

// It is always better to specify the else for an if otherwise the type
val ifWithoutElse = if (true) 2
val ifWithoutElseExpanded = if (true) 2: Int else (): Unit
// The common super-type between Int and Unit is AnyVal. This can be a bit surprising.
// Normally, you would want to avoid that.
def  sideEffectingFunction: Unit = if (true) println("Side effect!")

class Robot
val nano = new Robot
val vi = new Robot
// The eq operator can be handy to check if two references to an object are equal. If they
// are, then they both point to the same memory location
val pico = nano
pico eq nano
pico eq vi
// A class can have zero to many attributes / fields.

class Rectange (width: Double, height: Double) {
  val area: Double = width * height
  def scale(factor: Int): Rectange = {
    new Rectange(width * factor, height * factor)
  }
}

val square = new Rectange(width = 2, height = 2)
val x = square.area
val biggerSquare = square.scale( factor = 4)
val x = biggerSquare.area
// Why can we call members area and scale but not width? this is because the they are
// constructor arguments and can only be accessed within the class and not from the
// outside world. You need to prefix the variables with val if you want it to be accessible
// from the outside world.

class Circle(val radius: Double) {
  val PI: Double = scala.math.Pi

  val area: Double = BigDecimal(PI * radius * radius).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble

  val circumference: Double = BigDecimal(2 * PI * radius).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble

  def scale (factor: Int ) : Circle  =
    new Circle(radius * factor)
}

val myCircle = new Circle(radius = 7)
val x = myCircle.area
myCircle.circumference
myCircle.radius

// Inheritance is the way you expect in Scala

class Shape(val x: Double, val y: Double) {
  val isAtOrigin: Boolean = x == 0.0 && y == 0.0
  def description: String = s"Shape at ($x, $y)"
}

class ARectangle(x: Double, y: Double, val width: Double, val height: Double) extends Shape(x, y) {
  override def description: String = s"A Rectangle at ($x, $y)"
}
class Square (x: Double, y: Double, width: Double) extends ARectangle(x, y, width, width) {
  override def description: String = s"A Square at ($x, $y)"
}
class ACircle(x: Double, y: Double, val radius: Double) extends Shape(x,y) {}

val rect = new ARectangle(x = 0.0, y = 0.0, width = 10, height = 20)
rect.x
rect.y
rect.width
rect.height
rect.isAtOrigin
rect.description

val circle = new ACircle(2.0, 3.0, 7)
circle.description