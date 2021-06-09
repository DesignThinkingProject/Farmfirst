package com.example.farmfirst

import java.util.*


class DataModel(id: Int) {
    val title: String
    val subTitle: String
    val body: String

    init {
        title = String.format(Locale.ENGLISH, "Title %d Goes Here", id)
        subTitle = String.format(Locale.ENGLISH, "Sub title %d goes here", id)
        body = String.format(Locale.ENGLISH, "Body of this particular scheme, id")
    }
}