package com.mona.adel.ecommerceapp

import android.animation.ObjectAnimator
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        initializeSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun initializeSplashScreen(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            installSplashScreen()
            splashScreen.setOnExitAnimationListener {  splashScreenView->
                val slideUp = ObjectAnimator.ofFloat(
                    splashScreenView, View.TRANSLATION_X, 0f, -splashScreenView.height.toFloat()
                )
                slideUp.interpolator = AnticipateInterpolator()
                slideUp.duration = 3000L

                slideUp.doOnEnd { splashScreenView.remove() }

                slideUp.start()
            }
        }else{
            setTheme(R.style.Theme_ECommerceApp)
        }
    }
}