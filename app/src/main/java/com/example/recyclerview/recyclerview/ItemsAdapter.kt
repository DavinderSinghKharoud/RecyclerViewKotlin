package com.example.recyclerview.recyclerview

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.databinding.ItemBinding
import com.example.recyclerview.models.Item

class ItemsAdapter(
    private val items: MutableList<Item>
) : RecyclerView.Adapter<ItemsAdapter.itemViewHolder>() {


    class itemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var title: TextView = itemView.findViewById(R.id.tvItemTitle)
        private var checkBox: CheckBox = itemView.findViewById(R.id.cbItem)

        fun bind(item: Item) {
            title.text = item.title
            checkBox.isChecked = item.isChecked
            toggleStrikeCheckBox(title, checkBox.isChecked)
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeCheckBox(title, isChecked)
                item.isChecked = !item.isChecked
            }
        }

        private fun toggleStrikeCheckBox(title: TextView, checked: Boolean) {
            if (checked) {
                title.paintFlags = title.paintFlags or STRIKE_THRU_TEXT_FLAG
            } else {
                title.paintFlags = title.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
    }

    fun addItem(item: Item) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun deleteItems() {
        items.removeAll { item ->
            item.isChecked
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        return itemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        //Set values
        val curr = items[position]
        holder.bind(curr)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}