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

  // 3. We would like to write a utility function that, given a list of people, returns only the adults.
}
