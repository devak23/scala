package com.scala.myprograms.retcalc

import com.scala.myprograms.retcalc.RetirementCalculator.RetCalcParams
import org.scalactic.{Equality, TolerantNumerics, TypeCheckedTripleEquals}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/* TypeCheckedTripleEquals provides a powerful assertion "should ===" that ensures at compile time that both sides of
 * equality have the same type. the default ScalaTest assertion "should"  verifies the type equality at the runtime.
 * The "should ===" should always be used as it saves a lot of time when refactoring the code. It also allows us to
 * use certain amount of tolerance when using double values
 */
class RetirementCalculatorSpec extends AnyWordSpec with Matchers with TypeCheckedTripleEquals {
  /* This will allow double1 should === (double2)  assertion to pass if the absolute difference between the two is lower
   * than 0.0001. BigDecimals are to the rescue here, but for our purposes, we do not need the additional precision
   * offered by BigDecimal. Also, BigDecimal computations are much slower.
   */
  implicit val doubleEquality: Equality[Double] = TolerantNumerics.tolerantDoubleEquality(tolerance = 0.0001)

  "RetirementCalculator.futureCapital" should {
    "calculate the amount of savings I will have in n months" in {
      val actual = RetirementCalculator.futureCapital(
        FixedReturns(0.04)
        , noOfMonths = 25 * 12
        , netIncome = 3000
        , currentExpenses = 2000
        , initialCapital = 10000
      )

      val expected = 541267.1990
      actual should ===(expected)
    }
  }

  "calculate how much savings will be left after having taken a pension for n months" in {
    val actual = RetirementCalculator.futureCapital(
      returns = FixedReturns(0.04)
      , noOfMonths = 40 * 12
      , netIncome = 0
      , currentExpenses = 2000
      , initialCapital = 541267.1990
    )
    val expected = 309867.53176
    actual should ===(expected)
  }

  val params = RetCalcParams(
    noOfMonthsInRetirement = 40 * 12
    , netIncome = 3000
    , currentExpenses = 2000
    , initialCapital = 10000
  )

  "RetirementCalculator.simulatePlan" should {
    "calculate the capital at retirement and capital after death" in {
      val (capitalAtRetirement, capitalAfterDeath) = RetirementCalculator.simulatePlan(
        returns = FixedReturns(0.04)
        , noOfMonthsOfSaving = 25 * 12
        , params
      )

      capitalAtRetirement should ===(541267.1990)
      capitalAfterDeath should ===(309867.5316)
    }
  }

  "RetirementCalculator.noOfMonthsOfSaving" should {
    "calculate how long I need to save before I can retire" in {
      val actual = RetirementCalculator.noOfMonthsOfSaving(
        returns = FixedReturns(0.04)
        , params
      )
      val expected = 23 * 12 + 1
      actual should ===(expected)
    }

    "not crash if the resulting noOfMonths is very high" in {
      val actual = RetirementCalculator.noOfMonthsOfSaving(
        returns = FixedReturns(0.01)
        , params)
      val expected = 8280
      actual should ===(expected)
    }

    "not loop forever if I enter bad parameters" in {
      val actual = RetirementCalculator.noOfMonthsOfSaving(
        returns = FixedReturns(0.04)
        , params
      )

      actual should ===(Int.MaxValue)
    }
  }
}
