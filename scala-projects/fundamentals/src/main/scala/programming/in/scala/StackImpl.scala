package programming.in.scala

/**
 * An Implementation of a stack using a List
 * @tparam T - could be an element of any Type
 */
trait Stack[T] {
  def push (e: T): Stack[T]
  def pop (): (T, Stack[T])
  def peek: T
  def empty: Boolean
}

class StackImpl[T] extends Stack[T] {
  protected val elements: List[T] = Nil

  private def makeNewStack(es: List[T]): Stack[T] = new StackImpl[T] {
    override val elements: List[T] = es
  }

  override def push(e: T): Stack[T] = makeNewStack(e :: elements)

  override def pop(): (T, Stack[T]) = (elements.head, makeNewStack(elements.tail))

  override def peek: T = elements.head

  override def empty: Boolean = elements.isEmpty

  override def toString: String = elements.mkString(",")
}

object StackTest extends App {
  val elements: Stack[String] = new StackImpl[String]
      .push(" Ronnie O'Sullivan")
        .push(" Sachin Tendulkar")
        .push(" Bobby Fischer")
        .push(" Michael Phelps")
        .push(" Roger Federer")

  println(s"names = $elements")
  println(s"empty: ${elements.empty}")
  println(s"peek: ${elements.peek}")
  var (name, newElements) = elements.pop()
  println(s"name popped = $name and new stack: $newElements")
}
