package programming.in.scala

import scala.util.control.NonFatal

object NuclearMissile {

  case class Nuke()
  case class Target()
  case class Impacted()

  def arm: Nuke = throw new RuntimeException("Systems Offline")
  def aim: Target = throw new RuntimeException("Rotator jammed")

  def launch(target: Target, nuke: Nuke): Impacted = Impacted()

  def attackImperative: Impacted = {
    var impact: Impacted = null
    try {
      val nuke = arm
      val target = aim
      impact = launch(target, nuke)
    } catch {
      case NonFatal(e) => println("Nothing to worry about folks! Its a just a scala program.")
    }
    impact
  }

  def main(args: Array[String]): Unit = {
    val impacted: Impacted = attackImperative
    println(impacted)
  }
}
