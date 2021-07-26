import com.scala.myprograms.retcalc.{VariableReturn, VariableReturns}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/*
 * When we have VariableReturns over a long period, for instance, 1900 to 2017, it can be interesting to use a
 * smaller period to simulate what would happen if the historical returns in a smaller period, say 50 years, would be
 * repeated.
 */
class ReturnsSpec extends AnyWordSpec with Matchers {
  "VariableReturns.fromUntil" should {
    "keep only a window of the returns" in {
      /**
       * First, we generate a sequence of returns and assign them to variableReturns using the function Vector.tabulate.
       * It generates 12 elements, and each element is produced by an anonymous function, taking a parameter i that will
       * go from 0 to 11.
       */
      val variableReturns = VariableReturns(Vector.tabulate(12) {
        i =>
          val d = (i + 1).toDouble

          /**
           * In the call to the VariableReturn constructor, for the monthId argument, we use the f interpolator to
           * generate a string in the form 2017.01 when d = 1, 2017.02 when d = 2, and so on.
           */
          VariableReturn(f"2017.$d%02.0f", d)
      })

      /**
       * The fromUntil() that we are specifying will return a VariableReturns type that contains a specific window
       * inside the original returns. For now, we assume that the arguments passed to fromUntil are valid months that
       * are present in variableReturns. Ideally, we should add unit tests to specify what should happen if they are not.
       */
      variableReturns.fromUntil("2017.07", "2017.09").returns should ===(Vector(
        VariableReturn("2017.10", 10.0)
        , VariableReturn("2017.11", 11.0)
        , VariableReturn("2017.12", 12.0)
      ))
    }
  }
}
