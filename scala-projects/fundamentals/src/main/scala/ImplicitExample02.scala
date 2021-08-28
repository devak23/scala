object ImplicitExample02 extends App {

  implicit val rate: Int = 100
  def calcPayment(hours: Int) (implicit rate: Int):Int = hours * rate

  print(calcPayment(50)(110))
}
