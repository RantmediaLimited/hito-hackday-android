package com.allianz.hackday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_lottie_tabs.*

class LottieTabs : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie_tabs)

        lottieAnimationViewTab6.setOnClickListener {
            startTab6()
        }

        tab6.setOnClickListener {
            startTab6()
        }

        lottieAnimationViewTab5.setAnimation("tab_move.json")
        lottieAnimationViewTab1.setAnimation("tab_move.json")
        lottieAnimationViewTab1.repeatCount = 5000
        lottieAnimationViewTab1.playAnimation()

    }

    private fun startTab6() {
        if (!lottieAnimationViewTab6.isAnimating) {
            lottieAnimationViewTab6.setAnimation("tab_move.json")
            lottieAnimationViewTab6.repeatCount = 5000
            lottieAnimationViewTab6.playAnimation()
        }
    }
}
