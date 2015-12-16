package calculator

object Polynomial {
  def computeDelta(a: Signal[Double], b: Signal[Double],
      c: Signal[Double]): Signal[Double] = {

    if(a() == 0) Signal(0)
    else {

      //Δ = b² - 4ac
      val Δ = (b() * b())  - (4 * a() * c())
      Signal(Δ)

    }

  }

  def computeSolutions(a: Signal[Double], b: Signal[Double],
      c: Signal[Double], delta: Signal[Double]): Signal[Set[Double]] = {
   //(-b ± √Δ) / (2a)

    ???
  }
}
