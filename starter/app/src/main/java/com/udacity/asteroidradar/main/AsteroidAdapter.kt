package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.databinding.ListItemAsteroidBinding

/*
  Following Lesson 2.11 as a guide
*/
class AsteroidAdapter(val clickListener: AsteroidListener):
    ListAdapter<Asteroid, ViewHolder>(AsteroidDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

}

//class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)


class ViewHolder private constructor(private val binding: ListItemAsteroidBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(
        item: Asteroid,
        clickListener: AsteroidListener
    ) {
//        binding.asteroidNameText.text = item.codename
//        binding.passbyDate.text = item.closeApproachDate
//        binding.dangerIcon.setImageResource(
//            when (item.isPotentiallyHazardous) {
//                true -> R.drawable.ic_status_potentially_hazardous
//                else -> R.drawable.ic_status_normal
//            }
//        )
        binding.asteroid = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemAsteroidBinding.inflate(layoutInflater,parent,false)
            return ViewHolder(binding)
        }
    }

}

//per lesson 2.13
class AsteroidDiffCallback: DiffUtil.ItemCallback<Asteroid>(){
    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem == newItem
    }


}

//lesson 2.22
class AsteroidListener(val clickListener: (asteroid: Asteroid) -> Unit){
    fun onClick(asteroid: Asteroid) = clickListener(asteroid)
}