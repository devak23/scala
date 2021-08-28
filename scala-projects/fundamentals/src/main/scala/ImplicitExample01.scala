
object ImplicitExample01 extends App {
  implicit val rate: Int = 100

  def calcPayment(hours: Int) (implicit n: Int): Int = hours * n

  println(calcPayment(30))
}
