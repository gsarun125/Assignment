package com.mini.assignment1

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    private  lateinit var onboardingItemsAdapter: OnboardingItemsAdapter
    private  lateinit var indicatorsContainer:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnboardingItems()
        setupIndicators()
        setCurrentIndicator(0)

    }
    private fun navigateToHomeActtivity(){
        startActivity(Intent(applicationContext,HomeActivity::class.java))
        finish()
    }

    private fun setOnboardingItems(){
        onboardingItemsAdapter= OnboardingItemsAdapter(
            listOf(
                OnboardingItem(
                   onboardingImage= R.drawable.down,
                    title = "Upload",
                    description = "Share your image on the social media...!"
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.view,
                    title = "Beautiful things on earth..!",
                    description = "Most beautiful things in life are not to be heard, nor read about, nor seen, but, if one will, are to be lived."
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.con,
                    title = "About Us..!",
                    description = "However, the most amazing part is the history that displays the growth and journey. 'Enjoy the Beauty of the world'"
                )
            )
        )

        findViewById<TextView>(R.id.textSkip).setOnClickListener {
            navigateToHomeActtivity()
        }
        findViewById<Button>(R.id.buttonGetStarted).setOnClickListener {
            navigateToHomeActtivity()
        }



        val onboardingViewPager=findViewById<ViewPager2>(R.id.onboardingViewPager)
        onboardingViewPager.adapter=onboardingItemsAdapter
        onboardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int)
                {
                    super.onPageSelected(position)
                    setCurrentIndicator(position)
                }

        })
        (onboardingViewPager.getChildAt(0)as RecyclerView).overScrollMode=
            RecyclerView.OVER_SCROLL_NEVER
        findViewById<ImageView>(R.id.imageNext).setOnClickListener{
            if (onboardingViewPager.currentItem+1<onboardingItemsAdapter.itemCount){
                onboardingViewPager.currentItem+=1

            }else{
                navigateToHomeActtivity()
            }

        }
    }
    private fun setupIndicators(){
        indicatorsContainer=findViewById(R.id.indicatorsContainer)
        val indicators= arrayOfNulls<ImageView>(onboardingItemsAdapter.itemCount)
        val  layoutParams:LinearLayout.LayoutParams=
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for(i in indicators.indices)
        {
            indicators[i]= ImageView(applicationContext)
            indicators[i]?.let{
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams =layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }
    private  fun setCurrentIndicator(position: Int){
        val  childCount=indicatorsContainer.childCount
        for (i in 0 until childCount)
        {
            val imageView=indicatorsContainer.getChildAt(i)as ImageView
            if (i==position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            }else
            {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }
}