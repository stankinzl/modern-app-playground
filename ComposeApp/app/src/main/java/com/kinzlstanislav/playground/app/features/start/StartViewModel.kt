package com.kinzlstanislav.playground.app.features.start

import androidx.lifecycle.ViewModel
import com.kinzlstanislav.playground.core.extensions.coroutine
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class   StartViewModel : ViewModel() {

    val timeFlow: Flow<String> = flow {
        var timer = 1
        emit(timer.toString())
        coroutine {
            while(true) {
                delay(500)
                timer++
                emit(timer.toString())
            }
        }
    }
}