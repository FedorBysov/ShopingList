package com.example.shopinglist.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shopinglist.databinding.ActivityMainBinding
import com.example.shopinglist.presentation.adapter.AdapterMainActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var mainAdapter: AdapterMainActivity
//    private lateinit var linearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val buttonAddItem = binding.btnAdd
        buttonAddItem.setOnClickListener {
            val intent = ShopItemActivity.newIntentShopItemActivity(this)
            startActivity(intent)
        }

        setupAdapter()
        setupLongClick()

        setupClick()

        setupItemTouchHelper()


        viewModel.shopList.observe(this) {
            Log.d("TEST_LOADING_LIVE_DATA", "${it.toString()}")
            mainAdapter.submitList(it)
        }

    }


    private fun setupAdapter() {
        mainAdapter = AdapterMainActivity()
        binding.listRV.adapter = mainAdapter
        binding.listRV.recycledViewPool.setMaxRecycledViews(
            AdapterMainActivity.ENABLED_VIEW,
            AdapterMainActivity.POOL_VIEW_ACTIV
        )
        binding.listRV.recycledViewPool.setMaxRecycledViews(
            AdapterMainActivity.DISABLED_VIEW,
            AdapterMainActivity.POOL_VIEW_DISABLE
        )
    }

    private fun setupItemTouchHelper() {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = mainAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopList(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.listRV)
    }

    private fun setupClick() {
        mainAdapter.onShopItemCoinCliClickListener = {
            Log.d("onShopItemCoinCliClick", " ${it.toString()}")
            val intent = ShopItemActivity.editIntentShopItemActivity(this, it.id)
            startActivity(intent)
        }
    }

    private fun setupLongClick() {
        mainAdapter.onShopItemCoinLongCliClickListener = {
            viewModel.changeEnabledList(it)
        }
    }


//    fun showList(list: List<ShopItem>) {
//        binding.llShopList.removeAllViews()
//        for (shopItem in list) {
//            val layoutId = if (shopItem.enabled) {
//                R.layout.item_note_info
//            } else {
//                R.layout.item_hind_note_info
//            }
//            val view = LayoutInflater.from(this).inflate(layoutId, linearLayout, false)
//            val text = view.findViewById<TextView>(R.id.textNoteTV)
//            val count = view.findViewById<TextView>(R.id.countTV)
//            count.text = shopItem.count.toString()
//            text.text = shopItem.name
//            view.setOnLongClickListener {
//                viewModel.changeEnabledList(shopItem)
//                true
//            }
//            binding.llShopList.addView(view)
//
//        }
//    }
}