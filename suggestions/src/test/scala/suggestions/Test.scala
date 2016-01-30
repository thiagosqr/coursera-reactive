package suggestions

import rx.lang.scala.Observable
import scala.concurrent._
import scala.concurrent.duration._
import scala.io.StdIn


/**
  * Created by thiago-rs on 1/30/16.
  */
object Test {

  def main(args: Array[String]){

    Observable
      .interval(1 seconds)
      .take(4 seconds)
      .doOnNext(l => if (l > 2) throw new RuntimeException )
      .take(5 seconds)
      .doOnCompleted(println("Completed without error"))
      .onErrorResumeNext(e => Observable.interval(1 seconds).)
//      .doOnError(e => println(e))
      .subscribe{
        o => println(o)
      }

    StdIn.readLine()

  }


}
