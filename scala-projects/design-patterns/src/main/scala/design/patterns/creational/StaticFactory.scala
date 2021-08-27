package design.patterns.creational

trait Animal
class Bird extends Animal {
  override def toString: String = "I am bird"
}

class Mammal extends Animal {
  override def toString: String = "I am a Mammal"
}

class Fish extends Animal {
  override def toString: String = "I am a fish"
}

object Animal {
  def apply(animal: String): Animal = animal.toLowerCase match {
    case "bird" => new Bird
    case "mammal" => new Mammal
    case "fish" => new Fish
    case x: String => throw new IllegalArgumentException(s"Argument passed is invalid. Unknown animal $x")
  }
}

object StaticFactory extends App {

  val hummingBird = Animal("bird")
  println(hummingBird)

  val elephant = Animal("mammal")
  println(elephant)

  val shark = Animal("fish")
  println(shark)
}

// Here, every time we add a new extension of Animal, we would have to change the apply method to account for it,
// especially if we want to account for the new types.