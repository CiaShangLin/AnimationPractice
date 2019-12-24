package com.example.AnimationPractice

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RxTimerCount {
    private var time: Long = 0
    private var disposable: Disposable? = null
    private lateinit var observable: Observable<Long>
    private var consumer = Consumer<Long> {
        time++
        Log.d("TAG", "Time:$time")
        if (time == 10L) {
            disposable?.dispose()
            add()
        }
    }


    fun init() {
        observable = Observable.interval(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        disposable = observable.subscribe(consumer)
    }

    fun reStart() {
        disposable?.let {
            if (it.isDisposed) {
                Log.d("TAG", "reStart")
                disposable = observable.subscribe(consumer)
            }
        }
    }

    fun stop() {
        disposable?.let {
            if (!it.isDisposed) {
                it.dispose()
                Log.d("TAG", "stop")
            }
        }
    }

    private fun add() {
        Log.d("TAG", "ADD")
        disposable = null
    }
}