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
      val mb = (b() * -1)
      val sqrΔ = if(Math.sqrt(delta()).isNaN) 0 else Math.sqrt(delta())
      val m = (mb - sqrΔ) / (2 * a())
      val p = (mb + sqrΔ) / (2 * a())
      Signal(Set(m,p))
  }


}
