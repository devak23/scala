
object ImplicitExample03 extends App {
  case class Rate(value: Int)
  case class Age(value: Int)

  implicit val rate: Rate = Rate(100)
  implicit val age: Age = Age(40)

  def calcPayment(hours: Int) (implicit rate: Rate): Int = hours * rate.value
}
