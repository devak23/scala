package generics

case class Box[A](private var contents: A)

object Box {
  def put[A] (element: A, box: Box[A]):Unit = box.contents = element
  def get[A] (box: Box[A]): A = box.contents
}


