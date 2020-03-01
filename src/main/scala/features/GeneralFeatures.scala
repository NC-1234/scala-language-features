package features

object GeneralFeatures extends App {

  val aCondition: Boolean = false

  var aVariable = 42
  aVariable += 1 // 43

  //expressions
  val aConditionedVal = if (aCondition) 42 else 65

  //code block
  val aCodeBlock = {
    if (aCondition) 74
    else 56
  }

  //unit
  val theUnit = println("Hello, scala")

  def aFunction(x : Int): Int = x + 1
  //recursion - Tail Recursion
  def factorial(n: Int, acc: Int): Int = {
    if (n <= 0) acc
    else factorial(n - 1, acc * n)
  }

  //OOP

  class Animal
  class Dog extends Animal
  val aDog: Animal = new Dog

  trait carnivore {
    def eat(a: Animal): Unit
  }

  class Crocodile extends Animal with carnivore {
    override def eat(a: Animal): Unit = println("crunch!")
  }

  //method notations
  val aCroc = new Crocodile
  aCroc.eat(aDog)
  aCroc eat aDog

  val aCarnivore = new carnivore {
    override def eat(a: Animal): Unit = println("roar")
  }

  aCarnivore eat aDog

  //generics
  abstract class MyList[+A]
  // companion objects
  object MyList

  case class Person(name: String, age: Int)

  val aPotentialRuntimeError = try {
    throw new RuntimeException("I'm innocent")
  } catch {
    case e: Exception => "I've caught an exception"
  } finally {
    println("some logs")
  }

  val incrementer = new Function1[Int, Int] {
    def apply(v1: Int): Int = v1 + 1
  }

  val incremented = incrementer(42) // 43
  //incrementer.apply(42)

  val anonymousIncrementer = (x: Int) => x + 1
  // Int => Int === Function[Int, Int]

  // FP is all about working with functions as first class citizens
  List(1,2,3).map(incrementer)
  // map is a higher order function takes a function as a parameter basis of FP paradigm

  val pairs = for {
    num <- List(1,2,3)
    char <- List('a', 'b', 'c', 'd')
  } yield num + "-" + char

  //List(1,2,3,4).flatMap(num => List('a','b','c','d').map(char => num + "-" + char))

  //Option and try
  val anOption = Some(2)
  val aTry = try {
    throw new RuntimeException
  }

  //pattern matching
  val unknown = 2
  val order = unknown match {
    case 1 => "first"
    case 2 => "second"
    case _ => "unknown"
  }

  val bob = Person("bob", 22)
  val greeting = bob match {
    case Person(n, _) => s"Hi my name is $n"
    case _ => "I don't know my name"
  }

}
