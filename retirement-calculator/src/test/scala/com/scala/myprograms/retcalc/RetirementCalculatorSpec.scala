package com.scala.myprograms.retcalc

import org.scalactic.{Equality, TypeCheckedTripleEquals, TolerantNumerics}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/**
 * TypeCheckedTripleEquals provides a powerful assertion "should ===" that ensures at compile time that both sides of
 * equality have the same type. the default ScalaTest assertion "should"  verifies the type equality at the runtime.
 * The "should ===" should always be used as it saves a lot of time when refactoring the code. It also allows us to
 * use certain amount of tolerance when using double values
 */
class RetirementCalculatorSpec extends AnyWordSpec with Matchers with TypeCheckedTripleEquals {
  /**
   * This will allow double1 should === (double2)  assertion to pass if the absolute difference between the two is lower
   * than 0.0001. BigDecimals are to the rescue here, but for our purposes, we do not need the additional precision
   * offered by BigDecimal. Also, BigDecimal computations are much slower.
   */
  implicit val doubleEquality: Equality[Double] = TolerantNumerics.tolerantDoubleEquality(tolerance = 0.0001)

  "RetCalc.futureCapital" should {
    "calculate the amount of savings I will have in n months" in {
      val actual = RetirementCalculator.futureCapital(
        rateOfInterest = 0.04 / 12
        , noOfMonths = 25 * 12
        , netIncome = 3000
        , currentExpenses = 2000
        , initialCapital = 10000
      )

      val expected = 541267.1990
      actual should === (expected)
    }
  }
}
