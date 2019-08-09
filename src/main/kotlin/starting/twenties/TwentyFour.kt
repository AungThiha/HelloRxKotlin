package starting.twenties

import Jedi
import JediRank
import episodes
import exampleOf
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import romanNumeralIntValue

fun main(args: Array<String>) {

    exampleOf("map") {

        val subscriptions = CompositeDisposable()

        Observable.fromIterable(episodes)
                .map {
                    val space = " "
                    val components = it.split(space).toMutableList()
                    val number = components[1].romanNumeralIntValue()
                    components[1] = number.toString()
                    components.joinToString(space)
                }.subscribeBy {
                    println(it)
                }.addTo(subscriptions)

    }

    exampleOf("flatMap") {

        val subscriptions = CompositeDisposable()

        val ryan = Jedi(BehaviorSubject.createDefault(JediRank.Youngling))
        val charlotte = Jedi(BehaviorSubject.createDefault(JediRank.Youngling))

        val student = PublishSubject.create<Jedi>()

        student.flatMap {
            it.rank
        }.subscribeBy {
            println(it)
        }.addTo(subscriptions)

        student.onNext(ryan)

        ryan.rank.onNext(JediRank.Padawan)

        student.onNext(charlotte)

        ryan.rank.onNext(JediRank.JediKnight)

        charlotte.rank.onNext(JediRank.Padawan)

    }

    exampleOf("switchMap") {

        val subscriptions = CompositeDisposable()

        val ryan = Jedi(BehaviorSubject.createDefault(JediRank.Youngling))
        val charlotte = Jedi(BehaviorSubject.createDefault(JediRank.Youngling))

        val student = PublishSubject.create<Jedi>()

        student.switchMap {
            it.rank
        }.subscribeBy {
            println(it)
        }.addTo(subscriptions)

        student.onNext(ryan)

        ryan.rank.onNext(JediRank.Padawan)

        student.onNext(charlotte)

        ryan.rank.onNext(JediRank.JediKnight)

        charlotte.rank.onNext(JediRank.Padawan)

    }

}