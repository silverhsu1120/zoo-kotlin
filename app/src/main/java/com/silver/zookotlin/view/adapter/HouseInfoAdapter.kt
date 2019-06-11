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
import com.silver.zookotlin.model.bean.Plant
import kotlinx.android.synthetic.main.item_house_info.view.*
import kotlinx.android.synthetic.main.item_house_info.view.tv_info
import kotlinx.android.synthetic.main.item_house_info.view.tv_memo
import kotlinx.android.synthetic.main.item_plant.view.*
import kotlinx.android.synthetic.main.item_plant.view.iv_thumb
import kotlinx.android.synthetic.main.item_plant.view.tv_name


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

    private val plants = ArrayList<Plant>()

    fun load(plants: MutableList<Plant>) {
        this.plants.clear()
        this.plants.addAll(plants)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val resId = if (viewType == TYPE_HEADER) R.layout.item_house_info else R.layout.item_plant
        val view = LayoutInflater.from(parent.context).inflate(resId, parent, false)
        return if (viewType == TYPE_HEADER) HeaderHolder(view) else ViewHolder(view)
    }

    override fun getItemCount(): Int = plants.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderHolder -> holder.bind(house)
            is ViewHolder -> holder.bind(plants[position - 1])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER else TYPE_NORMAL
    }

    inner class HeaderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(house: House) {
            itemView.tv_info.text = house.info
            if (TextUtils.isEmpty(house.memo))
                itemView.tv_memo.setText(R.string.msg_memo)
            else
                itemView.tv_memo.text
            itemView.tv_category.text = house.category
            Glide.with(itemView.context)
                .load(house.picUrl)
                .apply(RequestOptions.errorOf(R.mipmap.ic_launcher))
                .into(itemView.iv_picture)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(plant: Plant) {
            itemView.tv_name.text = plant.chineseName
            itemView.tv_alias.text = plant.alias
            Glide.with(itemView.context)
                .load(plant.picUrl)
                .apply(RequestOptions.errorOf(R.mipmap.ic_launcher))
                .into(itemView.iv_thumb)
            itemView.setOnClickListener { listener.onItemClick(plant) }
        }
    }
}
