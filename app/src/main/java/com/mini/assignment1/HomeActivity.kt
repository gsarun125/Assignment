package com.mini.assignment1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2

class HomeActivity : AppCompatActivity() {

    private lateinit var onboardingItemsAdapter: OnboardingItemsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setOnboardingItems()
        findViewById<Button>(R.id.upload).setOnClickListener {
            navigateToUploadActtivity()
        }
    }

    private fun setOnboardingItems() {
        onboardingItemsAdapter = OnboardingItemsAdapter(
            listOf(
                OnboardingItem(
                    description = "dddddddddddddddddddd",
                    onboardingImage = R.drawable.a,
                    title = "aaaaaaaaaaaaaaaaaaaaaaa"

                )
            )
        )

        val onboardingViewPager=findViewById<ViewPager2>(R.id.onboardingViewPager)
        onboardingViewPager.adapter=onboardingItemsAdapter
        onboardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int)
            {
                super.onPageSelected(position)
            }

        })
    }

    private fun navigateToUploadActtivity(){
        startActivity(Intent(applicationContext,UploadActivity::class.java))
        finish()
    }
}