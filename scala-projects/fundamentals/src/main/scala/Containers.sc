import java.util.NoSuchElementException
import scala.::

// Declaring a Tuple
val personInfo = ("Abhay", "Kulkarni", "42", "M")
val genders = ("M","F")

// Iterating a Tuple
personInfo.productIterator.foreach(println)

// Accessing  a Tuple
val (firstName, lastName, age, sex) = personInfo
println(s"firstName: $firstName, lastName: $lastName, age: $age, sex: $sex")
val fullName = s"${personInfo._1} ${personInfo._2}"

// passing a part of the Tuple to function
def printPersonGender(name: String, gender: String) = println(s"Name: $name, M/F: $gender")
(printPersonGender _).tupled(personInfo._1, personInfo._4)

// length of the Tuple:
personInfo.productArity


// Creating a List
val weekdays = List("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
// list using the cons operator
val weekdays2 = List("Mon").::("Tue").::("Wed").::("Thu").::("Fri").::("Sat").::("Sun")
// Using the Nil in the end. The cons operator is a right associative operator that adds a value to a list
val weekdays3 = "Mon"::"Tue"::"Wed"::"Thu"::"Fri"::"Sat"::"Sun"::Nil
// The above way of creating a list is not elegant because it exposes the internal implementation of the list
// as a singly linked list. A singly Linked list are terminated with a special value - Nil. Nil is a special
// value and is technically a List[Nothing]

val part1 = List("Sun", "Mon", "Tue", "Wed")
val part2 = List("Thu", "Fri", "Sat")

val weekdays4 = List(part1, part2).flatten
val weekdays5 = part1 ++ part2
val weekdays6 = part1 ::: part2

val days = List(1,2,3,4,5,6,7,8,9)
val tupleOfDays = weekdays6 zip days

val headOfTheList = weekdays6.head
val tailOfTheList = weekdays6.tail
val sizeOfTheList = weekdays6.size
val mon = weekdays6(1)

// Maps are immutable associative containers (keys can be used to look up values)
val states = Map(
  "CA" -> "California",
  "NY" -> "New York",
  ("VT", "Vermont")
)

// Accessing a map via a key is BAU
val ny = states("VT")
// Accessing a non existent key results in an exception
try {
  println("Trying to get Texas from the Map")
  val tx = states("TX")
} catch {
  case e: NoSuchElementException => println("Oh no! Got NoSuchElementException.")
  case _:Throwable => println("Got  something else!")
}

val isTexasPresent = states.contains("TX")

// HOF's can also be used easily with the map
states.foreach((e: (String, String)) => println(s"${e._1} = ${e._2}"))

// Merge to lists into a Map
val states = List("California", "Vermont", "New York", "Texas")
val codes = List("CA", "VT", "NY", "TX")

val statesMap = (codes zip states).toMap
val isTexasPresent = statesMap.contains("TX")

// Convert a Map into a list
val codeList = statesMap.keys.toList
val stateList = statesMap.values.toList

// Options are containers (not collections) that allow us to perform type checking for null(None) values
// they make more sense in Scala than Java because the options can be used as a part of Pattern matching

def safeDivision (numerator: Int, denominator: Int): Option[Double] = {
  if (denominator == 0) None
  else Option(numerator/denominator)
}

val safeOutput1 = safeDivision(numerator = 22, denominator = 7)
val safeOutput2 = safeDivision(numerator = 22, denominator = 0)
val safeOutput3 = safeDivision(22, 7) getOrElse("Something went wrong!")
val safeOutput4 = safeDivision(22, 0) getOrElse("Something went wrong!")

safeDivision(22, 7) match {
  case Some(pi) => pi
  case None => "Something went wrong!!"
}

safeDivision(22, 0) match {
  case Some(value) => value
  case None => "Wrong denominator!!"
}

// util.try provides another way to cleanly check if there is a failure or not
val stateCode = util.Try(statesMap("Nonexitent state"))

stateCode match {
  case util.Success(code) => code
  case util.Failure(error) => s"Non existent state Code: $error"
}