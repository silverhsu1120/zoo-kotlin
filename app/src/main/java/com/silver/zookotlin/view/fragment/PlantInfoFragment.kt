package com.silver.zookotlin.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.silver.zookotlin.R
import com.silver.zookotlin.model.bean.Plant
import com.silver.zookotlin.viewmodel.PlantViewModel
import kotlinx.android.synthetic.main.fragment_plant_info.*
import kotlinx.android.synthetic.main.fragment_plant_info.view.*
import kotlinx.android.synthetic.main.view_section.view.*

private const val ARG_PLANT = "plant"

class PlantInfoFragment : Fragment() {

    private lateinit var viewModel: PlantViewModel

    private var plant: Plant? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { plant = it.getParcelable(ARG_PLANT) }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_plant_info, container, false)
        initView(view)
        initViewModel()
        return view
    }

    private fun initView(view: View) {
        view.view_section_name.tv_subject.text = plant?.chineseName
        view.view_section_alias.tv_subject.text = getString(R.string.alias)
        view.view_section_brief.tv_subject.text = getString(R.string.intro)
        view.view_section_feature.tv_subject.text = getString(R.string.feature)
        view.view_section_function.tv_subject.text = getString(R.string.function)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(PlantViewModel::class.java)
        viewModel.getPlant().observe(this, Observer { plant ->
            view_section_name.tv_message.text = plant?.englishName
            view_section_alias.tv_message.text = plant?.alias
            view_section_brief.tv_message.text = plant?.brief
            view_section_feature.tv_message.text = plant?.feature
            view_section_function.tv_message.text = plant?.function
            view_section_update.tv_message.text =
                String.format("%s : %s", getString(R.string.msg_update), plant?.update)
            Glide.with(context)
                .load(plant?.picUrl)
                .apply(RequestOptions.errorOf(R.mipmap.ic_launcher))
                .into(iv_picture)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPlant().value = plant
    }

    companion object {
        @JvmStatic
        fun newInstance(plant: Plant) =
            PlantInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PLANT, plant)
                }
            }
    }
}
