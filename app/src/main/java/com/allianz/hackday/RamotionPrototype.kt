package com.allianz.hackday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ramotion.paperonboarding.PaperOnboardingPage
import android.graphics.Color
import android.widget.Toast
import com.ramotion.paperonboarding.PaperOnboardingFragment


class RamotionPrototype : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ramotion_prototype)

        val page1 = PaperOnboardingPage(
            getString(R.string.hotels),
            getString(R.string.hotels_desc),
            Color.parseColor("#678FB4"),
            R.drawable.ic_hotel_black_24dp,
            R.drawable.ic_location_city_black_24dp
        )
        val page2 = PaperOnboardingPage(
            getString(R.string.banks),
            getString(R.string.banks_desc),
            Color.parseColor("#65B0B4"),
            R.drawable.ic_monetization_on_black_24dp,
            R.drawable.ic_attach_money_black_24dp
        )
        val page3 = PaperOnboardingPage(
            getString(R.string.stores),
            getString(R.string.stores_desc),
            Color.parseColor("#9B90BC"),
            R.drawable.ic_local_convenience_store_black_24dp,
            R.drawable.ic_shopping_basket_black_24dp
        )

        val elements = ArrayList<PaperOnboardingPage>()
        elements.add(page1)
        elements.add(page2)
        elements.add(page3)

        val onBoardingFragment = PaperOnboardingFragment.newInstance(elements)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container, onBoardingFragment)
        fragmentTransaction.commit()

        onBoardingFragment.setOnLeftOutListener {
            Toast.makeText(this, "out left", Toast.LENGTH_LONG).show()
        }

        onBoardingFragment.setOnRightOutListener {
            Toast.makeText(this, "out right", Toast.LENGTH_LONG).show()
        }

    }
}
