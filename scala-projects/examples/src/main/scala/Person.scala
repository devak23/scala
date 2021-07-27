case class Person (firstName: String, lastName: String, age: Int) {
  def description: String = s"Hi, I am ${firstName} ${lastName}. I am $age years old."
}

object Person {
  def filterAdult(people: List[Person]) : List[Person] = people.filter(p => p.age > 20)
}
