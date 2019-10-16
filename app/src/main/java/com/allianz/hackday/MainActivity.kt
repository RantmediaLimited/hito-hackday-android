package com.allianz.hackday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prototype1.setOnClickListener {
            startActivity(Intent(this, RamotionPrototype::class.java))
        }

        prototype2.setOnClickListener {
            startActivity(Intent(this, LottieTabs::class.java))
        }

        prototype3.setOnClickListener {
            startActivity(Intent(this, ProgrammaticTabsActivity::class.java))
        }

        prototype4.setOnClickListener {
            startActivity(Intent(this, SwipeActivity::class.java))
        }
    }
}
