package starting

import exampleOf
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

sealed class Droid : Throwable() {
    class OU812 : Droid()
}

sealed class FileReadError : Throwable() {
    class FileNotFound : FileReadError()
}

fun main(args: Array<String>) {
    exampleOf("create") {

        val subscriptions = CompositeDisposable()

        val droids = Observable.create<String> {emitter ->  
            emitter.onNext("R2-D2")
            emitter.onError(Droid.OU812())
            emitter.onNext("C-3PO")
            emitter.onNext("K2-SO")
        }

        val observer = droids.subscribeBy(
                onNext = { println(it) },
                onError = { println("Error, $it") },
                onComplete = { println("onCompleted") }
        )

        subscriptions.add(observer)

    }
}