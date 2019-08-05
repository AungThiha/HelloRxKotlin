package starting.onefigure

import HandError
import cardString
import cards
import exampleOf
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import points

fun main(args: Array<String>) {

    exampleOf("PublishSubject") {

        val subscriptions = CompositeDisposable()

        val dealtHand = PublishSubject.create<List<Pair<String, Int>>>()

        fun deal(cardCount: Int) {
            val deck = cards
            var cardsRemaining = deck.size // 52
            val hand = mutableListOf<Pair<String, Int>>()

            (0 until cardCount).forEach {
                val randomIndex = (0 until cardsRemaining).random()
                hand.add(deck[randomIndex])
                deck.removeAt(randomIndex)
                cardsRemaining -= 1

            }

            val totalPoints = points(hand)
            if (totalPoints > 21)
                dealtHand.onError(HandError.Busted())
            else
                dealtHand.onNext(hand)

        }

        subscriptions.add(dealtHand.subscribeBy(
                onNext = { println("${cardString(it)} ${points(it)}")},
                onError = { println(it) },
                onComplete = { println("complete")}
        ))

        deal(3)

        subscriptions.dispose()
    }
}