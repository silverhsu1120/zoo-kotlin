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
import com.silver.zookotlin.model.House
import com.silver.zookotlin.model.Plant
import com.silver.zookotlin.view.activity.MainActivity
import com.silver.zookotlin.view.adapter.HouseInfoAdapter
import com.silver.zookotlin.viewmodel.HouseViewModel
import kotlinx.android.synthetic.main.fragment_house_info.*
import kotlinx.android.synthetic.main.fragment_house_info.view.*

private const val ARG_HOUSE = "house"

class HouseInfoFragment : Fragment(), HouseInfoAdapter.OnItemClickListener {

    private lateinit var viewModel: HouseViewModel

    private var house: House? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { house = it.getParcelable(ARG_HOUSE) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_house_info, container, false)
        initView(view)
        initViewModel()
        return view
    }

    private fun initView(view: View) {
        view.rv_house_info.adapter = house?.let { HouseInfoAdapter(it, this) }
        view.rv_house_info.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        view.rv_house_info.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(HouseViewModel::class.java)
        viewModel.getHouse().observe(this, Observer<House> { house ->
            house?.let { (rv_house_info.adapter as HouseInfoAdapter).load(it) }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getHouse().value = this.house
    }

    override fun onItemClick(plant: Plant) {
        (activity as? MainActivity)?.displayPlantInfo(plant)
    }

    companion object {
        @JvmStatic
        fun newInstance(house: House) =
            HouseInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_HOUSE, house)
                }
            }
    }
}
