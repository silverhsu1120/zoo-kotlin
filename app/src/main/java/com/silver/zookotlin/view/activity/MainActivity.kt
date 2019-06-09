package com.silver.zookotlin.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.silver.zookotlin.R
import com.silver.zookotlin.model.House
import com.silver.zookotlin.model.Plant
import com.silver.zookotlin.view.fragment.HouseInfoFragment
import com.silver.zookotlin.view.fragment.HouseListFragment
import com.silver.zookotlin.view.fragment.PlantInfoFragment
import com.silver.zookotlin.viewmodel.NavBarViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG_HOUSE_LIST = "HouseListFragment"
        const val TAG_HOUSE_INFO = "HouseInfoFragment"
        const val TAG_PLANT_INFO = "PlantInfoFragment"
    }

    private lateinit var viewModel: NavBarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initViewModel()
    }

    private fun initView() {
        toolbar.setNavigationOnClickListener { processBackEvent() }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(NavBarViewModel::class.java)
        viewModel.getState().observe(this, Observer { state ->
            state?.getIcon()?.let { toolbar.setNavigationIcon(it) }
            toolbar.tv_title.text = state?.getTitle()
        })
    }

    override fun onResume() {
        super.onResume()
        displayHouseList()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        processBackEvent()
    }

    private fun displayHouseList() {
        viewModel.pushState(R.drawable.ic_menu_black, getString(R.string.app_name))
        addFragment(HouseListFragment.newInstance(), TAG_HOUSE_LIST)
    }

    fun displayHouseInfo(house: House) {
        viewModel.pushState(R.drawable.ic_arrow_back_black, house.name)
        addFragment(HouseInfoFragment.newInstance(house), TAG_HOUSE_INFO)
    }

    fun displayPlantInfo(plant: Plant) {
        viewModel.pushState(R.drawable.ic_arrow_back_black, plant.name)
        addFragment(PlantInfoFragment.newInstance(plant), TAG_PLANT_INFO)
    }

    private fun processBackEvent() {
        when (viewModel.getStack().size) {
            3 -> {
                viewModel.popState()
                supportFragmentManager.findFragmentByTag(TAG_PLANT_INFO)?.let { removeFragment(it) }
            }
            2 -> {
                viewModel.popState()
                supportFragmentManager.findFragmentByTag(TAG_HOUSE_INFO)?.let { removeFragment(it) }
            }
        }
    }

    private fun addFragment(f: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .add(R.id.fl_container, f, tag)
            .commitAllowingStateLoss()
    }

    private fun removeFragment(f: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .remove(f)
            .commitAllowingStateLoss()
    }
}
