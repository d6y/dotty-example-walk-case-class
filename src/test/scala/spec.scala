import org.junit.Test
import org.junit.Assert._
import scala.util._

import implied Read._

class ConverterSpec {

  case class Foo(a: Int) derives Read

  @Test def `lookup Read for Foo(Int)` = {
    val reader = the[Read[Foo]]
    val input= List("42")

    // One day:
    // val expected = Foo(42)
    //assertEquals(Success(expected), reader.read(input))

    // But for now:
    val expected = new java.lang.Exception("yay! it matched")
    assertEquals(Failure(expected).toString, reader.read(input).toString)
  }

}
