/**
 * This is a singleton. The equivalent of this in Java is a static class with static methods. Scala provides a
 * convenient class named 'App' that needs to be extended so that we can use it as the main entry point into our
 * application. The App superclass defines a static 'main' method that will execute all the code defined inside
 * 'Our Main Object'
 */
object Main extends App {

  val people = List(
    Person (firstName = "Soham", lastName = "Kulkarni", age = 8),
    Person (firstName = "Abhay", lastName = "Kulkarni", age = 42),
    Person (firstName = "Manik", lastName = "Kulkarni", age = 42),
    Person (firstName = "Suchitra", lastName = "Kulkarni", age = 65),
  )

  val descriptions = Person
    .filterAdult(people)
    .map(p => p.description).mkString("\n\t")

  println(s"The adults are \n\t$descriptions")
}
