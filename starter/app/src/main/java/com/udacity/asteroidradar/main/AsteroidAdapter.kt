package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.R

class AsteroidAdapter: RecyclerView.Adapter<ViewHolder>() {

    //per lesson 2.5
    var data = listOf<Asteroid>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_asteroid,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.asteroidName.text = item.codename
        holder.asteroidDate.text = item.closeApproachDate
        holder.dangerIcon.setImageResource(when(item.isPotentiallyHazardous){
            true -> R.drawable.ic_status_potentially_hazardous
            else -> R.drawable.ic_status_normal
        })
    }

    //per lesson 2.5
    override fun getItemCount() = data.size

}

//class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val asteroidName: TextView = itemView.findViewById(R.id.asteroid_name_text)
    val asteroidDate: TextView = itemView.findViewById(R.id.passby_date)
    val dangerIcon: ImageView = itemView.findViewById(R.id.danger_icon)
}