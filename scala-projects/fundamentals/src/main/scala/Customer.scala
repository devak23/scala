import java.util.UUID

final class Customer (first: String, last: String, email: String) {
  private val _id: UUID = UUID.randomUUID()
  private val _first: String = first
  private val _last: String = last
  private val _email: String = email

  def getId: UUID = _id
  def getFirst: String = _first
  def getLast: String = _last
  def getEmail: String = _email
}

object EmailRunner extends App {
  val tonyStark = new Customer(first = "Tony", last = "Stark", email = "tonystark@starkindustries.com")

  println(tonyStark.getId, tonyStark.getLast, tonyStark.getFirst, tonyStark.getEmail)
}
