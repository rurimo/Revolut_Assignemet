package com.benallouch.revolut.extension

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun startCoroutineTimer(
    delayMillis: Long = 1000, repeatMillis: Long = 1000, action: () -> Unit
) = GlobalScope.launch {
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