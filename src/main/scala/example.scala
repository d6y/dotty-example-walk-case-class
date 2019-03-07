
import scala.util.{ Success, Failure, Try }

trait Read[P] {
  def read(row: List[String]): Try[P]
}

object Read {

  import scala.reflect.Generic
  import scala.compiletime.erasedValue
  import scala.compiletime.Shape._

  inline def derived[P] given (ev: Generic[P]) = new Read[P] {
    def read(row: List[String]): Try[P] = {
      inline erasedValue[ev.Shape] match {
        case _ : Case[_, elems] => Failure(new Exception("yay! it matched"))
        case _  => Failure(new Exception("it did not match... boo"))
      }
    }
  }

}

