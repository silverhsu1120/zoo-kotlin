package com.silver.zookotlin.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.silver.zookotlin.R
import com.silver.zookotlin.model.House
import com.silver.zookotlin.model.Plant
import kotlinx.android.synthetic.main.item_house.view.*
import kotlinx.android.synthetic.main.item_house.view.tv_info
import kotlinx.android.synthetic.main.item_house.view.tv_intro
import kotlinx.android.synthetic.main.item_house_info.view.*

class HouseInfoAdapter(
    private var house: House,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_NORMAL = 0
        const val TYPE_HEADER = 1
    }

    interface OnItemClickListener {
        fun onItemClick(plant: Plant)
    }

    fun load(house: House) {
        this.house = house
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val resId = if (viewType == TYPE_HEADER) R.layout.item_house_info else R.layout.item_plant
        val view = LayoutInflater.from(parent.context).inflate(resId, parent, false)
        return if (viewType == TYPE_HEADER) HeaderHolder(view) else ViewHolder(view)
    }

    override fun getItemCount(): Int = house.plants.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderHolder -> holder.bind(house)
            is ViewHolder -> holder.bind(house.plants[position - 1])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER else TYPE_NORMAL
    }

    inner class HeaderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(house: House) {
            itemView.tv_name?.text = house.name
            itemView.tv_intro?.text = house.intro
            itemView.tv_info?.text = house.info
            itemView.iv_picture?.setImageResource(house.resId)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(plant: Plant) {
            itemView.tv_name?.text = plant.name
            itemView.tv_intro?.text = plant.intro
            itemView.iv_thumb?.setImageResource(plant.resId)
            itemView.setOnClickListener { listener.onItemClick(plant) }
        }
    }
}
