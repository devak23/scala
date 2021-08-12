package learning.scala.programming

object ForExpressions extends App {
  val person1 = new Person("Einstein", 67, 'M')
  val person2 = new Person("Newton", 51, 'M')
  val person3 = new Person("Pascal", 45, 'M')
  val person4 = new Person("Madam Curie", 40, 'M')

  val persons = List(person1, person2, person3, person4)

  for {
    person <- persons                       // This is called the generator expression
    age = person.age                        // definition
    name = person.name                      // definition
    if (age > 40 && name.startsWith("P"))   // filter expression
  } {
    println(s"Hey ${name}, You've won a free Gift Hamper")
  }

  /*
   * What if we want a couple more prizes for our people. In that case we may want to get a sub list of winners.
   * That's possible by introducing yield.
   */
  val winners = for {
    person <- persons
    age = person.age
    if age > 50
  } yield person

  println(s"Winners of the free Oregon trip are $winners")

  case class Person(name: String, age: Int, sex: Char) {
    override def toString: String = name
  }
}
