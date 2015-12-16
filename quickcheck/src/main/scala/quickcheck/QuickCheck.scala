package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  self =>

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

    if(a > aa)
      aa == min
    else
      a == min

  }

  property("empty") = forAll { a: A =>
    val h = insert(a,empty)
    isEmpty(deleteMin(h))
  }

  property("sortedseq") = forAll { (h: H, l: List[A]) =>
<<<<<<< HEAD

    val h = insertList(empty,l)
    val a = findMin(h)

    isSequenced(deleteMin(h), a)

  }

  property("ord") = forAll { (h: H, l: List[A]) =>


  }

  def insertList(h: H, l: List[A]): H = l match {
    case List() => h
    case x :: xs => insertList(insert(x,h), xs)
  }


  def isSequenced(h: H, lastMin: A): Boolean = {

    if(!isEmpty(h)){

      val curMin = findMin(h)

      if(lastMin > curMin) false
      else isSequenced(deleteMin(h), curMin)

    }else true
  }
=======

    val h = insertList(empty,l);
    val a = findMin(h)

    isSequenced(deleteMin(h), a)

  }


  def insertList(h: H, l: List[A]): H = l match {
    case List() => h
    case x :: xs => insertList(insert(x,h), xs)
  }

  def isSequenced(h: H, lastMin: A): Boolean = {

    if(!isEmpty(h)){

      val curMin = findMin(h)

      if(lastMin > curMin) false
      else isSequenced(deleteMin(h), curMin)
>>>>>>> f0f2032be5bd36220fc7d0227dbe34e8280d1599

    }else true

  }

  val emptyCons = const(empty)

  lazy val genListA = Gen.nonEmptyListOf[A](arbitrary[A])

  lazy val genHeap: Gen[H] = for {
    a <- arbitrary[A]
    h <- emptyCons
  } yield insert(a,h)


  lazy val genListA = Gen.containerOfN[List,A](10, arbitrary[A])


  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  implicit lazy val arbListA: Arbitrary[List[A]] = Arbitrary(genListA)

<<<<<<< HEAD
=======

>>>>>>> f0f2032be5bd36220fc7d0227dbe34e8280d1599
}
