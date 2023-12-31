package com.mini.assignment1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.nio.file.attribute.AclFileAttributeView

class OnboardingItemsAdapter(private val onboardingItems:List<OnboardingItem>):
RecyclerView.Adapter<OnboardingItemsAdapter.OnboardingItemViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        return OnboardingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.onboarding_item_container,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        holder.bind(onboardingItems[position])
    }

    override fun getItemCount(): Int {
        return onboardingItems.size
    }

    inner  class OnboardingItemViewHolder(view: View) :RecyclerView.ViewHolder(view){
        private val imageOnboarding=view.findViewById<ImageView>(R.id.imageOnboarding)
        private  val textTitle=view.findViewById<TextView>(R.id.textTitle)
        private  val textDiscription=view.findViewById<TextView>(R.id.textDiscription)
        fun bind(onboardingItem: OnboardingItem){
            imageOnboarding.setImageResource(onboardingItem.onboardingImage)
            textTitle.text=onboardingItem.title
            textDiscription.text=onboardingItem.description



        }
    }
}