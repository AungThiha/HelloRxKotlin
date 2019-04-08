package starting

import episodeI
import episodeIV
import episodeV
import episodeVI
import episodeVII
import exampleOf
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import rogueOne

fun main(args: Array<String>) {

    exampleOf("never") {

        val subscriptions = CompositeDisposable()

        val observable = Observable.never<Any>()

        val subscription = observable.doOnSubscribe {
            println("On Subscribe")
            println(it.isDisposed)
        }.doOnDispose {
            println("Disposed")
        }.subscribeBy(onNext = {
            println(it)
        }, onComplete = {
            println("Completed")
        })

        subscriptions.add(subscription)
    }

}