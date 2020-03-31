package com.benallouch.revolut.extension

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//inline function prevents the overhead of creating an object function each time we use it

var setStateJob: Job? = null

inline fun startCoroutineTimer(
    delayMillis: Long = 1000, repeatMillis: Long = 1000,
    crossinline action: () -> Unit
) {
    setStateJob?.cancel()
    setStateJob = GlobalScope.launch {
        delay(delayMillis)
        if (repeatMillis > 0) {
            while (true) {
                action()
                delay(repeatMillis)
            }
        } else {
            action()
        }
    }
}

fun cancelCoroutineTimer() {
    setStateJob?.cancel()
}
