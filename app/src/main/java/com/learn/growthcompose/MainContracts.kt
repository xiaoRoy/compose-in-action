package com.learn.growthcompose

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


interface MainPresenter {

    fun start()

    fun cleanup()

    fun triggerNotification()

    fun block()
}


interface MainViewRenderer {

    fun display()

    fun showNotification()

    fun showMessage()

}

class MainPresenterImpl(
    private val mainViewRenderer: MainViewRenderer
) : MainPresenter, CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext = job  + Dispatchers.Main

    override fun start() {
        launch {
            withContext(Dispatchers.IO) {
                delay(5000L)
            }
            mainViewRenderer.display()
        }
    }


    override fun triggerNotification() {
        launch {
            withContext(Dispatchers.IO) {
                delay(3000L)
            }
            mainViewRenderer.showNotification()
        }

    }

    override fun block() {

        launch {
            println("trail:threadName:" + Thread.currentThread().name)
            //it could still block  the thread!
            while (true);
            mainViewRenderer.showMessage()
        }

    }

    override fun cleanup() {
        cancel()
    }
}