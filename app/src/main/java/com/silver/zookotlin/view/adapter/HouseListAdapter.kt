package com.silver.zookotlin.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.silver.zookotlin.R
import com.silver.zookotlin.model.House
import kotlinx.android.synthetic.main.item_house.view.*

class HouseListAdapter(
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<HouseListAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(house: House)
    }

    private val houses: MutableList<House> = ArrayList()

    fun load(houses: MutableList<House>) {
        this.houses.clear()
        this.houses.addAll(houses)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_house, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = this.houses.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(houses[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(house: House) {
            itemView.tv_name?.text = house.name
            itemView.tv_intro?.text = house.intro
            itemView.tv_info?.text = house.info
            itemView.iv_thumb?.setImageResource(house.resId)
            itemView.setOnClickListener { listener.onItemClick(house) }
        }
    }
}
