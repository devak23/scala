// 3 properties that is required by any language to proclaim support for
// supporting first class functions


// 1. Functions should be able to be stored as values.
val compare = (s1: String, s2: String) => {
  if (s1 == s2) 0
  else if (s1 > s2) -1
  else 1
}: Int

compare("Soham", "Soham")
compare("Abhay", "Manik")
compare("Soham", "Manik")

val compareReverse = (s1: String, s2: String) => {
  if (s1 == s2) 0
  else if (s1 > s2) 1
  else -1
}: Int

// 2. A function should be able to be returned from another function
def getComparator (reverse: Boolean): (String, String) => Int = {
  if (reverse) compareReverse
  else compare
}

// The getComparator is passed reverse = true which returns the compare/compareReverse
// function which gets passed in the two strings - Manik and Abhay
val output1 = getComparator(true)("Manik","Abhay")
val output2 = getComparator(false)("Manik","Abhay")

// 3. Functions should be able to accept other functions as their parameters
// Following is an example of a higher order function (HOF). A HOF is a function whose return value
// is a function or whose parameter is a function. The return value or parameters should not be methods
// but this distinction doesn't matter a whole lot here because Scala is smart enough to convert the
// method into the functions

def smartCompare(s1: String, s2: String, cmpFn: (String, String) => Int): Int = {
  cmpFn(s1, s2)
}

val forward = smartCompare("Abhay", "Manik", compare)
val reverse = smartCompare("Abhay", "Manik", compareReverse)

def curriedCompare (cmpFn: (String, String) => Int) (s1: String, s2: String): Int = {
  cmpFn(s1, s2)
}
val curriedValue = curriedCompare(compare) ("Soham", "Manik")

// functions are basically expressions with parameters. Methods live within a class
// except the REPL where a method can be defined in line with a function, but that's
// only in REPL


val PI = 3.1415
val radius = 14

def areaOfCircle(r: Double): Double = PI * scala.math.pow(r, 2)

// line below flags an error because the compiler does NOT know if
// 1. You are going to invoke the the function areaOfCirlce which would return a Double OR
// 2. You are trying to convert the method into a function in which case the return value
// would be function1
// val calcAreaOfCircle = areaOfCircle

// So there are 2 ways to fix this. We define the type of calcAreaOfCircle as double
val calcAreaOfCircle1: (Double) => Double = areaOfCircle

// now since the return types of both areaOfCirlce and calcAreaOfCircle match, Scala
// has no problem. The other way is provided by the scala compiler itself by providing
// an underscore after the method name or within the parenthesis
val calcAreaOfCircle2 = areaOfCircle _
val calcAreaOfCircle3 = areaOfCircle(_)

calcAreaOfCircle1(radius)
calcAreaOfCircle2(radius)
calcAreaOfCircle3(radius)

// Shows a "closure" or a method that returns a function. What we want to do is invoke the method
// and store the returned function object in another function object to be used later outside the
// the original method
val getAreaClosure = {
  val PI = 3.14159
  val getArea = (r: Double) => {
    PI * math.pow(r, 2)
  }: Double
  getArea // this is where the function is being returned
}

// Following example shows a nested function. The return type of this method is a Tuple
def getCircleStats(PI:Double = 3.14159, r: Double): (Double, Double) = {
  def getCircleArea(r: Double): Double = PI * r * r
  def getCircumference(r: Double): Double = 2 * PI * r
  (getCircleArea(r), getCircumference(r))
}

getCircleStats(r = 10.0)

