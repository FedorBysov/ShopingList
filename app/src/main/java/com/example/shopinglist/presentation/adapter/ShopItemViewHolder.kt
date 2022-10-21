package com.example.shopinglist.presentation.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shopinglist.R

class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val text = view.findViewById<TextView>(R.id.textNoteTV)
    val count = view.findViewById<TextView>(R.id.countTV)
}
