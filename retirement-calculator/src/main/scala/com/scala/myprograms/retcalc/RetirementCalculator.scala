package com.scala.myprograms.retcalc

import scala.annotation.tailrec

object RetirementCalculator {
  //val log: Logger = Logger("RetirementCalculator")
  def noOfMonthsOfSaving2(rateOfInterest: Double
                          , noOfMonthsToRetirement: Int
                          , netIncome: Double
                          , currentExpenses: Double
                          , initialCapital: Double) :Int = {

    @tailrec
    def loop(months: Int): Int = {
      val (_, capitalAfterDeath) = simulatePlan(rateOfInterest, months, noOfMonthsToRetirement, netIncome, currentExpenses, initialCapital)
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

  def noOfMonthsOfSaving(rateOfInterest: Double
                         , noOfMonthsToRetirement: Int
                         , netIncome: Double
                         , currentExpenses: Double
                         , initialCapital: Double):Int = {
    @tailrec
    def loop(months: Int): Int = {
      val (_, capitalAfterDeath) = simulatePlan(rateOfInterest, months, noOfMonthsToRetirement, netIncome, currentExpenses, initialCapital)
      if (capitalAfterDeath > 0.0)
        months
      else
        loop(months + 1)
    }

    loop(0)
  }

  def simulatePlan(rateOfInterest: Double
                   , noOfMonthsOfSaving: Int
                   , noOfMonthsInRetirement: Int
                   , netIncome: Double
                   , currentExpenses: Double
                   , initialCapital: Double): (Double, Double) =  {
    val capitalAtRetirement = futureCapital(rateOfInterest, noOfMonthsOfSaving, netIncome, currentExpenses, initialCapital)
    val capitalAfterDeath = futureCapital(rateOfInterest, noOfMonthsInRetirement, 0, currentExpenses, capitalAtRetirement)
    (capitalAtRetirement,capitalAfterDeath)
  }

  def futureCapital(rateOfInterest: Double
                    , noOfMonths: Int
                    , netIncome: Double
                    , currentExpenses: Double
                    , initialCapital: Double): Double = {
    val monthlySavings = netIncome - currentExpenses

    /*
     * For month 0, before any savings, we have a capital (lets call it cap0) of (say 10,000). For month 1, our capital
     * generated some interest. That amount = capital0 * rate of interest. We also saved 1000 more.
     * So we have cap0 + cap0 * rate of interest + monthly savings. Putting it into the formula, we have
     * cap0 (1 + rate of interest) + monthly savings. This entire thing becomes a capital for the next month (say cap1)
     * Thus cap1 = cap0 * (1 + monthly rate of interest) + monthly savings.
     * For month 2, we have cap2 = cap1 * (1 + monthly rate of interest) + monthly savings
     */

    def nextCapital (accumulated: Double, noOfMonths: Int): Double = accumulated * (1 + rateOfInterest) + monthlySavings

    /*
     * We iterate through the months and apply foldLeft which is similar to a reduce function used in most of the programming
     * languages. The foldLeft has two parameter lists. Scala allows you to have many parameter lists. Each list can
     * have one or many parameters. This does not change the behavior of the function rather, its just a way of
     * separating the concerns. The foldLeft takes an operator function (next capital) and acts on the accumulator
     * (initialCapital) for each member of the collection (0 to noOfMonths). It will return the accumulator once it has
     * finished iterating through all the members of the collection. Note that we don't make use of the noOfMonths in
     * the nextCapital but we need to define it because the operator function in the foldLeft requires it to be defined.
     */
    (0 until noOfMonths).foldLeft(initialCapital)(nextCapital)

    /**
     * Refactoring the above two lines, we can shorten our implementation as follows:
     * (0 until noOfMonths).foldLeft(initialCapital)((accumulated,_) => accumulated * (1 + rateOfInterest) + monthlySavings)
     * Since the noOfMonths is not used it can be substituted with an underscore '_'. The '_' cannot be used within a
     * function.
     */
  }
}

