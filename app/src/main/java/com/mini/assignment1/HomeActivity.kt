package com.mini.assignment1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
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
        findViewById<Button>(R.id.feeds).setOnClickListener {
            navigateToFeedActtivity()
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


    private fun navigateToFeedActtivity(){
        startActivity(Intent(applicationContext,FeedActivity::class.java))
        finish()
    }
}