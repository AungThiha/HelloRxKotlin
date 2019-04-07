package starting

import episodeIV
import episodeV
import episodeVI
import exampleOf
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable

fun main(args: Array<String>){

    exampleOf("subscribe") {

        val observable = Observable.just(episodeIV, episodeV, episodeVI)

        observable.subscribe {
            println(it)
        }
    }

    exampleOf("subscribeBy") {

        val observable = Observable.just(episodeIV, episodeV, episodeVI)

        observable.subscribeBy(
                onNext = { println(it) },
                onComplete = { println("Completed") }
        )
    }

    exampleOf("empty") {

        val observable = Observable.empty<Unit>()

        observable.subscribeBy(
                onNext = { println(it) },
                onComplete = { println("Completed") }
        )
    }


    exampleOf("never") {

        val observable = Observable.never<Any>()

        observable.subscribeBy(
                onNext = { println(it) },
                onComplete = { println("Completed") }
        )
    }

    exampleOf("dispose") {

        val mostPopular = Observable.just(episodeIV, episodeV, episodeVI)

        val subscription = mostPopular.subscribe {
            println(it)
        }

        subscription.dispose()
    }

    exampleOf("CompositeDisposable") {

        val subscriptions = CompositeDisposable()


        subscriptions.add(listOf(episodeIV, episodeV, episodeVI)
                .toObservable()
                .subscribe {
                    println(it)
                }
        )

    }


}