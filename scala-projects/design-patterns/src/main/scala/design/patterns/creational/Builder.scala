package design.patterns.creational

// let's put a constraint on the example and say that every person must have at least firstName and lastName specified.
// In order to make the compiler aware that fields are being set, this needs to be encoded as a type.
sealed trait BuildStep
sealed trait HasFirstName extends BuildStep
sealed trait HasLastName extends BuildStep

class Person(val firstName: String, val lastName: String, val age: Int)

class PersonBuilder[PassedStep <: BuildStep] private (var firstName: String, var lastName: String, var age: Int) {
  protected def this() = this("","", 0)
  protected def this(pb: PersonBuilder[_]) = this (pb.firstName, pb.lastName, pb.age)


  def setFirstName(firstName: String): PersonBuilder[HasFirstName] = {
    this.firstName = firstName
    new PersonBuilder[HasFirstName](this)
  }

  def setLastName(lastName: String): PersonBuilder[HasLastName] = {
    this.lastName = lastName
    new PersonBuilder[HasLastName](this)
  }

  def setAge(age: Int): PersonBuilder[PassedStep] = {
    this.age = age
    this
  }

  // The following syntax sets a generalized type constraint and says that build can only be called on a builder, which
  // has passed the HasLastName step
  def build()(implicit ev: PassedStep =:= HasLastName): Person = new Person(firstName, lastName, age)
}

// We need to allow our users to create a builder using another method, since all constructors are invisible to the
// outside world. That's why we should add a companion object, as shown here. The companion object uses one of the
// constructors we previously defined and it also makes sure the object returned is at the right build step
object PersonBuilder {
  def apply() = new PersonBuilder[BuildStep]()
}

object Builder extends App {

}
