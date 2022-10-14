package com.example.shopinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.shopinglist.R
import com.example.shopinglist.databinding.ActivityMainBinding
import com.example.shopinglist.domain.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var linearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        linearLayout = binding.llShopList
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.shopList.observe(this) {
            Log.d("TEST_LOADING_LIVE_DATA", "${it.toString()}")
            showList(it)
        }

    }

    fun showList(list: List<ShopItem>) {
        binding.llShopList.removeAllViews()
        for (shopItem in list) {
            val layoutId = if (shopItem.enabled) {
                R.layout.item_note_info
            } else {
                R.layout.item_hind_note_info
            }
            val view = LayoutInflater.from(this).inflate(layoutId, linearLayout, false)
            val text = view.findViewById<TextView>(R.id.textNoteTV)
            val count = view.findViewById<TextView>(R.id.countTV)
            count.text = shopItem.count.toString()
            text.text = shopItem.name
            view.setOnLongClickListener {
                viewModel.changeEnabledList(shopItem)
                true
            }
            binding.llShopList.addView(view)

        }
    }
}