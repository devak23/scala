import java.util.concurrent.TimeUnit

object ImplicitExample04 extends App {
  import scala.concurrent._
  import java.util.concurrent.Executors

  val executor = Executors.newFixedThreadPool(4)
  val executionContext: ExecutionContext = ExecutionContext.fromExecutor(executor)

  val future = Future.apply{
    println(s"Thread-name: ${Thread.currentThread().getName}")
    TimeUnit.SECONDS.sleep(3)
    50 + 100
  } (executionContext)

  future.map(x => x * 100) (executionContext)
    .foreach(a => println(a))(executionContext)

  TimeUnit.SECONDS.sleep(5)
  System.exit(0)
}
