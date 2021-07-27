import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MainSpec extends AnyWordSpec with Matchers {
  // 1. We would like to create a Person class and test its constructor
  "A Person" should {
    "be instantiated with an age and a name" in {
      val abhay = Person (firstName = "abhay", lastName = "kulkarni", age = 42)
      abhay.firstName should be ("abhay")
      abhay.lastName should be ("kulkarni")
      abhay.age should be (42)
    }
  }

  // 2. We would want a description of the Person
  "should have human readable representation of himself" in {
    val paul = Person (firstName = "Paul", lastName = "Crane", age = 53)
    paul.description should be ("Hi, I am Paul Crane. I am 53 years old.")
  }

  "companion object" should {
    val (akira, petra, nick ) = (
      Person ("Akira", "Sakura", 5),
      Person ("Petra", "Muller", 34),
      Person ("Nick", "Tagart", 43)
    )

    // 3. We would like to write a utility function that, given a list of people, returns only the adults.
    "should return a list of adult person in a given list" in {
      val ref = List(akira, petra, nick)
      Person.filterAdult(ref) should be (List(petra, nick))
    }

    // 4. If no adult found in the list, the function needs to return an empty list.
    "should return an empty list if no adult in the list " in {
      val ref = List(petra, nick, akira)
      Person.filterAdult(ref) should be(List.empty[Person])
    }
  }
}
