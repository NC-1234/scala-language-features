package features

import scala.concurrent.Future
import scala.util.{Failure, Success}

object MultithreadingFeatures extends App {

  //creating threads on the JVM

  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("Hi")
  })

  val thread = new Thread(() => println("I'm running in parallel"))
  thread.start()
  thread.join()

  val threadHello = new Thread(() => (1 to 1000).foreach(_ => println("hello")))
  val threadGoodBye = new Thread(() => (1 to 1000).foreach(_ => println("goodbye")))

  //different runs create different results

  class BankAccount(@volatile private var amount: Int) {
    override def toString: String = "" + amount

    def withdraw(money: Int) = this.amount -= money
    //Not atomic therefore not thread safe

    def safeWithdraw(money: Int) = this.synchronized {
      this.amount -= money
    }
    //this is thread safe no to threads can access this at the same time
  }

  //wait-notify mechanisms
  //scala futures

  import scala.concurrent.ExecutionContext.Implicits.global
  val future = Future {
    //long computation
    42
  }

  //callbacks
  future.onComplete {
    case Success(42) => println("I found the meaning of life")
    case Failure(_) => println("sometheing happened with the meaning of life")
  }

  val aProcessedFuture = future.map(_ + 1) //Future 43
  val aFlatFuture = future.flatMap { value =>
    Future( value + 2 )
  } //Future 44

  val filteredFuture = future.filter(_ % 2 == 0) //NoSuchElementException if this is false

  //for comprehensions
  val aNoneseneFuture = for {
    meaningOfLife <- future
    filterMeaningOfLife <- filteredFuture
  } yield meaningOfLife + filterMeaningOfLife

}
