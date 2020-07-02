package com.rantmedia.hackday

import android.app.Activity
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import kotlinx.android.synthetic.main.activity_motion_editor.*


class MotionEditorActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_editor)

        val motionContainer =  findViewById<MotionLayout>(R.id.motion_layout)

        blue_animation_btn.setOnClickListener {
            motionContainer.setTransition(R.id.blue_transition)
            motionContainer.transitionToEnd()
        }

        orange_animation_btn.setOnClickListener {
            motionContainer.setTransition(R.id.orange_transition)
            motionContainer.transitionToEnd()
        }

        curve_btn.setOnClickListener {
            motionContainer.setTransition(R.id.both_transition)
            motionContainer.transitionToEnd()
        }
    }
}