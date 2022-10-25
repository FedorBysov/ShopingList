package com.example.shopinglist.presentation

import android.content.Context
import android.content.Intent
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

//interface FedyaBissov {
//   fun fedyaDelaetPost(data: ContactsContract.Contacts.Data): String
//   fun fedyaGotovitVMultivarke()
//}

class ShopItemFragment(
    private val shopItemId: Int = ShopItem.Unknown_ID.toString(),
    private val screenMode: String = MODE_UNKNOWN
) : Fragment() {

//    override fun fedyaDelaetPost(data: ContactsContract.Contacts.Data): String {
//        return "jopa"
//    }
//    override fun fedyaGotovitVMultivarke(){
//
//    }

    private lateinit var viewModel: ShopItemViewModel
    private var _binding: FragmentShopItemBinding? = null
    private val binding
        get() = _binding!!


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
        _binding = FragmentShopItemBinding.inflate(inflater, container, false)
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
            viewModel.finishWork() //TODO("Проверить работу")
        }

    }

    private fun launchEmdit() {
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
        if (screenMode != MODE_ADD && screenMode != MODE_EDIT) {
            throw RuntimeException("Param Screen Mode is Absent")
        }
        if (screenMode == MODE_EDIT && shopItemId == ShopItem.Unknown_ID) {
            if (!requireActivity().intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
                throw RuntimeException("Param Shop item id is absent")
            }
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

        fun newIntentShopItemActivity(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun editIntentShopItemActivity(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
            return intent
        }
    }

}