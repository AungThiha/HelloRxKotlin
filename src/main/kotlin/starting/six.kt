package starting

import exampleOf
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import java.io.File
import kotlin.text.Charsets.UTF_8

sealed class Droid : Throwable() {
    class OU812 : Droid()
}

sealed class FileReadError : Throwable() {
    class FileNotFound : FileReadError()
}

fun main(args: Array<String>) {

    exampleOf("create") {

        val subscriptions = CompositeDisposable()

        val droids = Observable.create<String> { emitter ->
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

    exampleOf("Single") {

        val subscriptions = CompositeDisposable()

        fun loadText(filename: String): Single<String> {
            return Single.create { emitter ->
                val file = File(filename)

                if (!file.exists()){
                    return@create emitter.onError(FileReadError.FileNotFound())
                }

                val contents = file.readText(UTF_8)

                return@create emitter.onSuccess(contents)
            }
        }

        val observer = loadText("ANewHope.txt")
                .subscribe({
                    println(it)
                }, {
                    println("Error, $it")
                })

        subscriptions.add(observer)

    }

}