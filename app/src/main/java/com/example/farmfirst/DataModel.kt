package com.example.farmfirst

import android.text.Editable
import java.util.*


data class DataModel(val title: String,val subTitle: String,val body: String) {

    companion object {
        fun genDataModels(n: Int): ArrayList<DataModel> {
            val DataModelArray = ArrayList<DataModel>(n)

            for (i in 1..n) {
                var dataModeldetail = DataModel(
                    "Title Goes here",
                    "Subtitle goes here",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam tincidunt quam nibh, sed eleifend justo interdum sit amet. Donec est nibh, euismod eu metus cursus, luctus pulvinar magna. Curabitur blandit ex vitae erat lobortis, rutrum sollicitudin nunc auctor. Aenean dapibus urna quis lacinia mollis.\"\n"
                )
                DataModelArray.add(dataModeldetail)
            }
            return DataModelArray
        }
    }

}
