package com.silver.zookotlin.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.silver.zookotlin.R
import com.silver.zookotlin.model.Plant
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
        view.view_section_alias?.tv_subject?.text = getString(R.string.alias)
        view.view_section_intro?.tv_subject?.text = getString(R.string.intro)
        view.view_section_type?.tv_subject?.text = getString(R.string.type)
        view.view_section_use?.tv_subject?.text = getString(R.string.use)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(PlantViewModel::class.java)
        viewModel.getPlant().observe(this, Observer { plant ->
            plant?.resId?.let { iv_picture.setImageResource(it) }
            view_section_alias?.tv_message?.text = plant?.alias
            view_section_intro?.tv_message?.text = plant?.intro
            view_section_type?.tv_message?.text = plant?.type
            view_section_use?.tv_message?.text = plant?.use
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
