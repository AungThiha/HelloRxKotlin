package starting.onefigure

import Droid
import episodeIIIMovie
import episodeIIMovie
import episodeIMovie
import episodeIVMovie
import exampleOf
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import tomatometerRatings

fun main(args: Array<String>) {

    exampleOf("skipWhile") {

        val subscriptions = CompositeDisposable()

        subscriptions.add(
                Observable.fromIterable(tomatometerRatings)
                        .skipWhile {
                            it.rating < 90
                        }.subscribe {
                            println(it)
                        }
        )

    }

    exampleOf("skipUntil") {

        val subscriptions = CompositeDisposable()

        val subject = PublishSubject.create<String>()
        val trigger = PublishSubject.create<Unit>()

        subscriptions.add(
                subject.skipUntil(trigger)
                        .subscribe {
                            println(it)
                        }
        )

        subject.onNext(episodeIMovie.title)
        subject.onNext(episodeIIMovie.title)
        subject.onNext(episodeIIIMovie.title)

        trigger.onNext(Unit)

        subject.onNext(episodeIVMovie.title)

    }


    exampleOf("takeWhile") {

        val subscriptions = CompositeDisposable()

        subscriptions.add(
                Observable.fromIterable(tomatometerRatings)
                        .takeWhile {
                            it.rating < 90
                        }.subscribe {
                            println(it)
                        }
        )

    }

    exampleOf("takeUntil") {

        val subscriptions = CompositeDisposable()

        val subject = PublishSubject.create<String>()
        val trigger = PublishSubject.create<Unit>()

        subscriptions.add(
                subject.takeUntil(trigger)
                        .subscribe {
                            println(it)
                        }
        )

        subject.onNext(episodeIMovie.title)
        subject.onNext(episodeIIMovie.title)
        subject.onNext(episodeIIIMovie.title)

        trigger.onNext(Unit)

        subject.onNext(episodeIVMovie.title)

    }

    exampleOf("distinctUntilChanged") {

        val subscriptions = CompositeDisposable()

        subscriptions.add(
                Observable.just(Droid.R2D2, Droid.C3PO, Droid.C3PO, Droid.R2D2)
                        .distinctUntilChanged()
                        .subscribe {
                            println(it)
                        }
        )

    }

}