package pravda.forth

import utest._
import pravda.test.utils.IntegrationUtils._

object ForthTest extends TestSuite {

  def tests = Tests {
    "A forth compiler must correctly define and run word" - {
      assert(
        runForthWithoutEnviroment[Int](": seq5 1 2 3 ; seq5") == Right(List(1, 2, 3))
      )
    }

    "A forth program must be able to push to the stack" - {
      assert(
        runForthWithoutEnviroment[Int]("1") == Right(List(1))
      )

      assert(
        runForthWithoutEnviroment[Int]("1 2") == Right(List(1, 2))
      )

      assert(
        runForthWithoutEnviroment[Int]("1 2 3") == Right(List(1, 2, 3))
      )
    }

    "A forth standard library must define +" - {
      assert(
        runForthWithoutEnviroment[Int]("3 5 add") == Right(List(8))
      )
    }

    "A forth standard library must define *" - {
      assert(
        runForthWithoutEnviroment[Int]("1 2 3 *") == Right(List(1, 6))
      )
    }

    "A forth must  work with hex strings" - {

      assert(
        runForthWithoutEnviroment[List[Byte]]("$xFFF1F2") == Right(List(List(0xFF.toByte, 0xF1.toByte, 0xF2.toByte)))
      )

    }
  }

}