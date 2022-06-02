package com.learn.growthcompose

import android.animation.ObjectAnimator
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.DEFAULT_SOUND
import androidx.core.app.NotificationCompat.DEFAULT_VIBRATE
import androidx.core.app.NotificationManagerCompat
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.splashscreen.SplashScreenViewProvider
import androidx.fragment.app.FragmentActivity

class MainActivity : AppCompatActivity(), MainViewRenderer {

    private lateinit var mainPresenter: MainPresenter

    private lateinit var container: View

    private var isReady = false

    private lateinit var splashScreen: SplashScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()
        mainPresenter = MainPresenterImpl(this)
        container = findViewById(R.id.view_main_container)
        container.setOnClickListener {
            mainPresenter.block()
        }
//        what()
//        container.viewTreeObserver.addOnPreDrawListener(
//            object : ViewTreeObserver.OnPreDrawListener {
//                override fun onPreDraw(): Boolean {
//                    println("")
//                    // Check if the initial data is ready.
//                    return if (isReady) {
//                        // The content is ready; start drawing.
//                        container.viewTreeObserver.removeOnPreDrawListener(this)
//                        true
//                    } else {
//                        // The content is not ready; suspend.
//                        false
//                    }
//                }
//            }
//        )
        mainPresenter.start()

    }

    private fun what() {
        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            val duration = splashScreenViewProvider.iconAnimationDurationMillis
            val start = splashScreenViewProvider.iconAnimationStartMillis
            val splashScreenView = splashScreenViewProvider.view
            val slideBack = ObjectAnimator.ofFloat(splashScreenView, View.TRANSLATION_X, 0f, -splashScreenView.width.toFloat()).apply {
                interpolator = DecelerateInterpolator()
                this.duration = 800L
                doOnEnd { splashScreenViewProvider.remove() }
            }

            slideBack.start()

        }
    }

    private fun createNotificationChannel() {

        val channel: NotificationChannelCompat = NotificationChannelCompat.Builder("567", NotificationManagerCompat.IMPORTANCE_HIGH)
            .setName("Test")
            .setDescription("descriptionText")
            .build()
        NotificationManagerCompat.from(this).createNotificationChannel(channel)
    }

    override fun showNotification() {
        val notification = NotificationCompat.Builder(this, "567")
            .setContentTitle("Test")
            .setContentText("This is  a test.")
            .setColor(getColor(android.R.color.transparent))
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setSmallIcon(androidx.appcompat.R.drawable.abc_ab_share_pack_mtrl_alpha).build()
        NotificationManagerCompat.from(this).notify(123, notification)
    }

    override fun display() {
        isReady = true

    }

    override fun showMessage() {
        println("trail.showMessage")
    }

    override fun onDestroy() {
        mainPresenter.cleanup()
        super.onDestroy()
    }


}