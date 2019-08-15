package starting.thirties

import bowcaster
import chewbacca
import defender
import dl44
import episodeI
import episodeII
import episodeIV
import episodeV
import exampleOf
import hanSolo
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import leia
import lightsaber
import luke
import runtimes
import stringFrom

fun main(args: Array<String>) {

    exampleOf("combineLatest") {

        val subscriptions = CompositeDisposable()

        val characters = PublishSubject.create<String>()
        val primaryWeapons = PublishSubject.create<String>()

        Observables.combineLatest(characters, primaryWeapons) { character, weapon ->
            "$character: $weapon"
        }.subscribeBy {
            println(it)
        }.addTo(subscriptions)

        characters.onNext(luke)
        primaryWeapons.onNext(lightsaber)

        characters.onNext(hanSolo)
        primaryWeapons.onNext(dl44)

        characters.onNext(leia)
        primaryWeapons.onNext(defender)

        characters.onNext(chewbacca)
        primaryWeapons.onNext(bowcaster)

    }


    exampleOf("zip") {

        val subscriptions = CompositeDisposable()

        val characters = PublishSubject.create<String>()
        val primaryWeapons = PublishSubject.create<String>()

        Observables.zip(characters, primaryWeapons) { character, weapon ->
            "$character: $weapon"
        }.subscribeBy {
            println(it)
        }.addTo(subscriptions)

        characters.onNext(luke)
        primaryWeapons.onNext(lightsaber)

        characters.onNext(hanSolo)
        primaryWeapons.onNext(dl44)

        characters.onNext(leia)
        primaryWeapons.onNext(defender)

        characters.onNext(chewbacca)
        primaryWeapons.onNext(bowcaster)

    }

    exampleOf("ambWith") {

        val subscriptions = CompositeDisposable()

        val prequelEpisodes = PublishSubject.create<String>()
        val originalEpisodes = PublishSubject.create<String>()

        prequelEpisodes.ambWith(originalEpisodes)
                .subscribeBy {
                    println(it)
                }.addTo(subscriptions)

        prequelEpisodes.onNext(episodeI)
        originalEpisodes.onNext(episodeIV)
        originalEpisodes.onNext(episodeV)
        prequelEpisodes.onNext(episodeII)

    }

    exampleOf("reduce") {

        val subscriptions = CompositeDisposable()

        Observable.fromIterable(runtimes.values)
                .reduce { a, b -> a + b }
                .subscribeBy(
                        onSuccess = { println(stringFrom(it)) },
                        onError = { }
                ).addTo(subscriptions)

    }

    exampleOf("scan") {

        val subscriptions = CompositeDisposable()

        Observable.fromIterable(runtimes.values)
                .scan { a, b -> a + b }
                .subscribeBy(
                        onNext = { println(stringFrom(it)) },
                        onError = { }
                ).addTo(subscriptions)

    }


}