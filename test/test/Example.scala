package test

import scalacheck.Prop._

object MathLib extends scalacheck.Testable {

  property("sqr", (n: Int) => 
    sqr(n) == n*n
  )
 
  def sqr(n: Int): Int = n*n


  property("max", (n: Int, m: Int) => {
    val mx = max(m,n)
    mx >= m && mx >= n && (mx == m || mx == n)
  })

  def max(n: Int, m: Int) = if(n >= m) n else n

}

object TestMathLib extends Application {

  // Either use scalacheck's test executer

  Console.println("\nExecuting tests with ScalaCheck...\n")
  
  MathLib.check()


  // ... or use SUnit

  Console.println("\nExecuting tests with SUnit...\n")

  import scala.testing.SUnit._

  val suite = new TestSuite(MathLib.testCases: _*)
  val r = new TestResult

  suite.run(r)

  for(val tf <- r.failures()) {
     Console.println( tf.toString())
  }

}