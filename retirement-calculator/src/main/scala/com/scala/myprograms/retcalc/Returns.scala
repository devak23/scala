package com.scala.myprograms.retcalc

sealed trait Returns

case class FixedReturns(annualRate: Double) extends Returns

/* A Vector is recommended when we need to model a sequence of elements as it is faster than List for appending/inserting
 * elements or accessing them by index
 */
case class VariableReturns(returns: Vector[VariableReturn]) extends Returns {
  def fromUntil(monthIdFrom: String, monthUntil: String): VariableReturns = {
    VariableReturns (
      /*
       * We use the higher order function dropWhile to drop elements until we reach the condition monthId == monthIdFrom.
       * Then, we call takeWhile on the resulting collection to keep all elements until monthId == monthIdUntil. This
       * will return a collection that keeps only the elements in a window that starts at monthIdFrom and ends just
       * before monthIdUntil.
       */
      returns
        .dropWhile(_.monthId != monthIdFrom)
        .takeWhile(_.monthId != monthUntil)
    )
  }
}

/*
 * For VariableReturn, we keep the monthly interest rate and an identifier monthId that will have the form 2017.02,
 * for February 2017.
 */
case class VariableReturn(monthId: String, monthRate: Double)