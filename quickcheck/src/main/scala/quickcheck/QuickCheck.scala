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

  property("meld") = forAll { (a: A, aa: A) =>
    val h1 = insert(a,empty)
    val h2 = insert(aa,empty)

    val h3 = meld(h1, h2)
    val min = findMin(h3)

    (min == a || min == aa) && !isEmpty(h3)

  }

  property("empty") = forAll { a: A =>
    val h = insert(a,empty)
    isEmpty(deleteMin(h))
  }

  property("sortedseq") = forAll { h: H =>



    def insertList(h: H, l: List[A]): H = l match {
      case List() => h
      case x :: xs => insertList(insert(x,h), xs)
    }

    def isSequenced(h: H, lastMin: A): Boolean = {

      val curMin = findMin(h)

      if(isEmpty(h)) true
      else if(lastMin > curMin) false
      else isSequenced(deleteMin(h), curMin)
    }

    val l = arbitrary[A].map

    val h = insertList(empty,l);
    val a = findMin(h)

    isSequenced(deleteMin(h), a)

  }




  val emptyCons = const(empty)

  lazy val genHeap: Gen[H] = for {
    a <- arbitrary[A]
    h <- emptyCons
  } yield insert(a,h)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

}
