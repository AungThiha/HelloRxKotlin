package starting

import episodeI
import episodeII
import episodeIII
import episodeIV
import episodeIX
import episodeV
import episodeVI
import episodeVII
import episodeVIII
import rogueOne
import solo
import exampleOf
import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable

fun main(args: Array<String>){
    exampleOf("creating observables") {
        val mostPopular = Observable.just(episodeV)
        val originalTrilogy = Observable.just(episodeIV, episodeV, episodeVI)
        val prequelTrilogy = Observable.just(listOf(episodeI, episodeII, episodeIII))
        val sequelTrilogy = Observable.fromIterable(listOf(episodeVII, episodeVIII, episodeIX))
        val stories = listOf(solo, rogueOne).toObservable()
    }
}