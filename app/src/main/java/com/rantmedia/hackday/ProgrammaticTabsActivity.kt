package com.rantmedia.hackday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_programmatic_tabs.*
import android.animation.ValueAnimator
import android.os.Handler
import android.widget.ImageView

const val ANIMATION_DURATION = 200L

class ProgrammaticTabsActivity : AppCompatActivity() {
    var selectedTab = 1
    var expandedHeight = 50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_programmatic_tabs)

        setTabClickListener(tab1)
        setTabClickListener(tab2)
        setTabClickListener(tab3)
        setTabClickListener(tab4)
    }

    private fun getSelectedTabView(): View {
        return when (selectedTab) {
            1 -> tab1IV
            2 -> tab2IV
            3 -> tab3IV
            4 -> tab4IV
            else -> tab1IV
        }
    }

    private fun setTabClickListener(view: View) {
        view.setOnClickListener {
            selectedTab = when (view.id) {
                R.id.tab1 -> 1
                R.id.tab2 -> 2
                R.id.tab3 -> 3
                R.id.tab4 -> 4
                else -> 1
            }
            setTabsEnabled(false)
            collapseAllTabs()
            Handler().postDelayed({
                setTabsEnabled(true)
                raiseSelectedTab()
            }, ANIMATION_DURATION/2)
        }
    }

    private fun raiseSelectedTab() {
        val tab = getSelectedTabView()

        if (tab.measuredHeight != expandedHeight) {
            val animation = ValueAnimator.ofInt(0, expandedHeight)
            animation.addUpdateListener { valueAnimator ->
                val valAnim = valueAnimator.animatedValue as Int
                val layoutParams = tab.layoutParams
                layoutParams.height = valAnim
                tab.layoutParams = layoutParams
            }


            animation.duration = ANIMATION_DURATION
            animation.start()
        }
    }

    private fun setTabsEnabled(enabled: Boolean) {
        tab1.isEnabled = enabled
        tab2.isEnabled = enabled
        tab3.isEnabled = enabled
        tab4.isEnabled = enabled
    }

    private fun collapseAllTabs() {
        if (selectedTab != 1) {
            collapseTab(tab1IV)
        }
        if (selectedTab != 2) {
            collapseTab(tab2IV)
        }
        if (selectedTab != 3) {
            collapseTab(tab3IV)
        }
        if (selectedTab != 4) {
            collapseTab(tab4IV)
        }
    }

    private fun collapseTab(tab: ImageView) {
        if (tab.measuredHeight != 0 && expandedHeight == 50) {
            expandedHeight = tab.measuredHeight
        }
        val animation = ValueAnimator.ofInt(tab.measuredHeight, 0)
        animation.addUpdateListener { valueAnimator ->
            val valAnim = valueAnimator.animatedValue as Int
            val layoutParams = tab.layoutParams
            layoutParams.height = valAnim
            tab.layoutParams = layoutParams
        }

        animation.duration = ANIMATION_DURATION
        animation.start()
    }

}
