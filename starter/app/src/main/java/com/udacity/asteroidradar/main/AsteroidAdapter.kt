package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.R

class AsteroidAdapter: RecyclerView.Adapter<TextItemViewHolder>() {

    //per lesson 2.5
    var data = listOf<Asteroid>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view,parent,false) as TextView
        return TextItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.codename
    }

    //per lesson 2.5
    override fun getItemCount() = data.size

}

class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)