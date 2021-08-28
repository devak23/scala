import java.util.concurrent.TimeUnit

object ImplicitExample05 extends App {
  import java.util.concurrent.Executors
  import scala.concurrent._

  val executor = Executors.newFixedThreadPool(5)
  implicit val executionContext: ExecutionContext = ExecutionContext.fromExecutor(executor)

  val future = Future.apply{
    println(s"Thread-name: ${Thread.currentThread().getName}")
    TimeUnit.SECONDS.sleep(4)
    50 + 20
  } (executionContext)

  future.map(x => x * 100)
    .foreach(a => println(a))

  TimeUnit.SECONDS.sleep(5)
  System.exit(0)
}
