package com.silver.zookotlin.model

import android.content.Context
import com.silver.zookotlin.R

class DataGenerator {

    companion object {
        fun getHouseList(context: Context): MutableList<House> {
            val houses: MutableList<House> = ArrayList()
            houses.add(getInsetHouse(context))
            houses.add(getKoalaHouse(context))
            houses.add(getInsetHouse(context))
            houses.add(getKoalaHouse(context))
            houses.add(getInsetHouse(context))
            houses.add(getKoalaHouse(context))
            houses.add(getInsetHouse(context))
            houses.add(getKoalaHouse(context))
            return houses
        }

        private fun getInsetHouse(context: Context): House {
            return House(
                context.getString(R.string.insect_house),
                context.getString(R.string.msg_insect_house),
                context.getString(R.string.msg_no_closed_info),
                R.drawable.ic_butterfly,
                getPlants(context)
            )
        }

        private fun getKoalaHouse(context: Context): House {
            return House(
                context.getString(R.string.koala_house),
                context.getString(R.string.msg_koala_house),
                context.getString(R.string.msg_no_closed_info),
                R.drawable.ic_koala,
                getPlants(context)
            )
        }

        private fun getPlants(context: Context): MutableList<Plant> {
            val plants: MutableList<Plant> = ArrayList()
            plants.add(getFireSpike(context))
            plants.add(getFireSpike(context))
            plants.add(getFireSpike(context))
            plants.add(getFireSpike(context))
            plants.add(getFireSpike(context))
            return plants
        }

        private fun getFireSpike(context: Context): Plant {
            return Plant(
                context.getString(R.string.fire_spike),
                context.getString(R.string.msg_fire_spike_alias),
                context.getString(R.string.msg_fire_spike_intro),
                context.getString(R.string.msg_fire_spike_type),
                context.getString(R.string.msg_fire_spike_use),
                R.drawable.ic_fire_spike
            )
        }
    }
}
