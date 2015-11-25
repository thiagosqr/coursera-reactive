package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  property("min1") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
  }

  property("gen1") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h))==m
  }

  property("deleteMinimum") = forAll { a: A =>
    val h = insert(a,empty)
    isEmpty(deleteMin(h))
  }


  val emptyCons = const(empty)

  lazy val genHeap: Gen[H] = for {
    a <- arbitrary[A]
    h <- oneOf(emptyCons, genHeap)
  } yield insert(a,h)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

}
