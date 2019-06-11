package com.silver.zookotlin.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.silver.zookotlin.R
import com.silver.zookotlin.model.bean.House
import com.silver.zookotlin.view.activity.MainActivity
import com.silver.zookotlin.view.adapter.HouseListAdapter
import com.silver.zookotlin.viewmodel.HouseListViewModel
import kotlinx.android.synthetic.main.fragment_house_list.*
import kotlinx.android.synthetic.main.fragment_house_list.view.*

class HouseListFragment : Fragment(), HouseListAdapter.OnItemClickListener {

    private lateinit var viewModel: HouseListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_house_list, container, false)
        initView(view)
        initViewModel()
        return view
    }

    private fun initView(view: View) {
        view.rv_house.adapter = HouseListAdapter(this)
        view.rv_house.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        view.rv_house.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(HouseListViewModel::class.java)
        viewModel.getHouses().observe(this, Observer<MutableList<House>> { houses ->
            houses?.let { (rv_house.adapter as HouseListAdapter).load(it) }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchHouseList(null, null, null)
    }

    override fun onItemClick(house: House) {
        (activity as? MainActivity)?.displayHouseInfo(house)
    }

    companion object {
        @JvmStatic
        fun newInstance() = HouseListFragment()
    }
}
