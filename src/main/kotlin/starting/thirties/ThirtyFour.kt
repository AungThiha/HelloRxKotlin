package starting.thirties

import exampleOf
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import runtimes
import stringFrom

fun main(args: Array<String>) {

    exampleOf("zip + scan") {

        val subscriptions = CompositeDisposable()

        val runtimeKeys = Observable.fromIterable(runtimes.keys)
        val runtimeValues = Observable.fromIterable(runtimes.values)

        val scanTotals = Observable.fromIterable(runtimes.values)
                .scan { a, b -> a + b }

        Observables.zip(
                runtimeKeys,
                runtimeValues,
                scanTotals
        ).subscribeBy {
            val (movieTitle, runtime, runtimeTotal) = it
            println(
                    "$movieTitle: ${stringFrom(runtime)} (${stringFrom(runtimeTotal)})"
            )
        }.addTo(subscriptions)

    }


}