package com.scala.myprograms.retcalc

import scala.annotation.tailrec

object RetirementCalculator {

  def noOfMonthsOfSaving(returns: Returns
                         , params: RetCalcParams):Int = {
    import params._
    @tailrec
    def loop(months: Int): Int = {
      val (_, capitalAfterDeath) = simulatePlan(returns, months, params)
      if (capitalAfterDeath > 0.0)
        months
      else
        loop(months + 1)
    }

    if (netIncome > currentExpenses)
      loop(0)
    else
      Int.MaxValue
  }

  def simulatePlan(returns: Returns
                   , noOfMonthsOfSaving: Int
                   , params: RetCalcParams): (Double, Double) =  {
    import params._
    // accumulation phase
    val capitalAtRetirement = futureCapital(returns, noOfMonthsOfSaving, netIncome, currentExpenses, initialCapital)

    // decumulation phase
    val capitalAfterDeath = futureCapital(OffsetReturns(returns, noOfMonthsOfSaving), noOfMonthsInRetirement, 0, currentExpenses, capitalAtRetirement)
    (capitalAtRetirement,capitalAfterDeath)
  }

  def futureCapital(returns: Returns
                    , noOfMonths: Int
                    , netIncome: Double
                    , currentExpenses: Double
                    , initialCapital: Double): Double = {
    val monthlySavings = netIncome - currentExpenses
    (0 until noOfMonths).foldLeft(initialCapital) {
      case (accumulated, month) => accumulated * (1 + Returns.monthlyRate(returns, month)) + monthlySavings
    }

  }

  case class RetCalcParams(noOfMonthsInRetirement: 480, netIncome: Int, currentExpenses: Int, initialCapital: Int)
}

