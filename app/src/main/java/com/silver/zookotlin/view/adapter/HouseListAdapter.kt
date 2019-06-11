package com.silver.zookotlin.view.adapter

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.silver.zookotlin.R
import com.silver.zookotlin.model.bean.House
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
            itemView.tv_info?.text = house.info
            if (TextUtils.isEmpty(house.memo))
                itemView.tv_memo.setText(R.string.msg_memo)
            else
                itemView.tv_memo.text = house.memo
            Glide.with(itemView.context)
                .load(house.picUrl)
                .apply(RequestOptions.errorOf(R.mipmap.ic_launcher))
                .into(itemView.iv_thumb)
            itemView.setOnClickListener { listener.onItemClick(house) }
        }
    }
}
