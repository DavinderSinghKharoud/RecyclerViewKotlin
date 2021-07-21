package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.models.Item
import com.example.recyclerview.recyclerview.ItemsAdapter

class MainActivity : View.OnClickListener, AppCompatActivity() {
    private lateinit var mainView: ActivityMainBinding
    private lateinit var mItemAdapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainView = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainView.root)

        mItemAdapter = ItemsAdapter(mutableListOf())
        mainView.rvItems.apply {
            adapter = mItemAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            isNestedScrollingEnabled = false
        }
        mainView.bAddItems.setOnClickListener(this)
        mainView.bDeleteItem.setOnClickListener(this)

        for (num in 1..10000){
            mItemAdapter.addItem(Item("Hello"))
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.bAddItems -> {
                val title: String = mainView.etAddItem.text.toString()
                if (title.isNotEmpty()) {
                    mItemAdapter.addItem(Item(title))
                    mainView.etAddItem.text.clear()
                }
            }
            R.id.bDeleteItem ->{
                mItemAdapter.deleteItems()
            }
        }
    }
}