package com.example.shopinglist.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.shopinglist.R
import com.example.shopinglist.databinding.FragmentShopItemBinding
import com.example.shopinglist.domain.ShopItem
import com.google.android.material.textfield.TextInputLayout


class ShopItemFragment : Fragment() {


    private lateinit var viewModel: ShopItemViewModel
    private var _binding: FragmentShopItemBinding? = null
    private val binding
        get() = _binding!!

    private var screenMode: String = MODE_UNKNOWN
    private var shopItemId: Int = ShopItem.Unknown_ID

    private lateinit var tilName: TextInputLayout
    private lateinit var tilCount: TextInputLayout
    private lateinit var etName: EditText
    private lateinit var etCount: EditText
    private lateinit var buttonSave: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShopItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parserMetod()

        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]

        initViews()

        etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetInputName()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        etCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetInputCount()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        when (screenMode) {
            MODE_EDIT -> launchEdit()
            MODE_ADD -> launchAdd()
        }
        viewModel.errorInputCount.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_name)
            } else {
                null
            }
            tilCount.error = message
        }

        viewModel.errorInputName.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_count)
            } else {
                null
            }
            tilName.error = message
        }

        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            activity?.onBackPressed()
        }

    }

    private fun launchEdit() {
        viewModel.getShopItem(shopItemId)
        viewModel.shopItem.observe(viewLifecycleOwner) {
            etName.setText(it.name)
            etCount.setText(it.count.toString())
        }
        buttonSave.setOnClickListener {
            viewModel.editShopItem(etName.text?.toString(), etCount.text?.toString())
        }
    }

    private fun launchAdd() {
        buttonSave.setOnClickListener {
            viewModel.addShopItem(etName.text?.toString(), etCount.text?.toString())
        }
    }

    private fun parserMetod() {
        val args = requireArguments()
        if (!args.containsKey(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param Screen Mode is Absent")
        }
        val mode = args.getString(EXTRA_SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("UNKNOWN screen $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(EXTRA_SHOP_ITEM_ID)) {
                throw RuntimeException("Param Shop item id is absent")
            }
            shopItemId = args.getInt(EXTRA_SHOP_ITEM_ID,ShopItem.Unknown_ID)

        }

    }


    private fun initViews() {
        tilName = binding.tilName
        tilCount = binding.tilCount
        etCount = binding.etCount
        etName = binding.etName
        buttonSave = binding.btnSave
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_EDIT = "edit_mode"
        private const val MODE_ADD = "add_mode"

        private const val MODE_UNKNOWN = ""

//        fun newIntentShopItemActivity(context: Context): Intent {
//            val intent = Intent(context, ShopItemActivity::class.java)
//            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
//            return intent
//        }
//
//        fun editIntentShopItemActivity(context: Context, shopItemId: Int): Intent {
//            val intent = Intent(context, ShopItemActivity::class.java)
//            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
//            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
//            return intent
//        }

        fun newIntentAddFragment(): Fragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_SCREEN_MODE,MODE_ADD)
                }
            }
        }

        fun intentEditFragment(shopItemId: Int): Fragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_SCREEN_MODE,MODE_EDIT)
                    putInt(EXTRA_SHOP_ITEM_ID, shopItemId)
                }
            }
        }
    }

}