package starting.thirties

import episodeI
import episodeII
import episodeIII
import episodeIV
import episodeV
import episodeVI
import exampleOf
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import rogueOne
import solo
import theCloneWars

fun main(args: Array<String>) {

    exampleOf("startWith") {

        val subscriptions = CompositeDisposable()

        val prequelEpisodes = Observable.just(episodeI, episodeII, episodeIII)

        val flashback = prequelEpisodes.startWith(listOf(episodeIV, episodeV))

        flashback
                .subscribeBy { episode ->
                    println(episode)
                }.addTo(subscriptions)

    }

    exampleOf("concatWith") {

        val subscriptions = CompositeDisposable()

        val prequelTrilogy = Observable.just(episodeI, episodeII, episodeIII)
        val originalTrilogy = Observable.just(episodeIV, episodeV, episodeVI)

        prequelTrilogy.concatWith(originalTrilogy)
                .subscribeBy { episode ->
                    println(episode)
                }.addTo(subscriptions)

    }

    exampleOf("mergeWith") {

        val subscriptions = CompositeDisposable()

        val filmTrilogies = PublishSubject.create<String>()
        val standAloneFilms = PublishSubject.create<String>()

        filmTrilogies.mergeWith(standAloneFilms)
                .subscribeBy { episode ->
                    println(episode)
                }.addTo(subscriptions)

        filmTrilogies.onNext(episodeI)
        filmTrilogies.onNext(episodeII)

        standAloneFilms.onNext(theCloneWars)

        filmTrilogies.onNext(episodeIII)

        standAloneFilms.onNext(solo)
        standAloneFilms.onNext(rogueOne)

        filmTrilogies.onNext(episodeIV)

    }

}