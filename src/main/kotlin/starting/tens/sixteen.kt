package starting.tens

import detours
import exampleOf
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import landOfDroids
import liveLongAndProsper
import mayTheForce
import mayTheOdds
import tomatometerRatings
import wookieWorld

fun main(args: Array<String>) {

    exampleOf("IgnoreElements") {

        val subscriptions = CompositeDisposable()

        val cannedProjects = PublishSubject.create<String>()

        cannedProjects.ignoreElements()
                .subscribeBy {
                    println("Completed")
                }.addTo(subscriptions)

        cannedProjects.onNext(landOfDroids)
        cannedProjects.onNext(wookieWorld)
        cannedProjects.onNext(detours)

        cannedProjects.onComplete()

    }

    exampleOf("ElementAt") {

        val subscriptions = CompositeDisposable()

        val quotes = PublishSubject.create<String>()

        quotes.elementAt(2) // Returns a MayBe, Subscribe with onSuccess instead of onNext
                .subscribeBy(
                        onSuccess = { println(it) },
                        onComplete = { println("Completed") },
                        onError = { println("Error ${it.localizedMessage}") }
                ).addTo(subscriptions)

        quotes.onNext(mayTheOdds)
        quotes.onNext(liveLongAndProsper)
        quotes.onNext(mayTheForce)

        quotes.onNext(mayTheForce)

    }

    exampleOf("Filter") {

        val subscriptions = CompositeDisposable()

        Observable.fromIterable(tomatometerRatings)
                .filter {
                    it.rating >= 90
                }
                .subscribeBy {
                    println(it)
                }.addTo(subscriptions)
    }

}