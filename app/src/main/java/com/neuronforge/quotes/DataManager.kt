package com.neuronforge.quotes

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.google.gson.Gson
import com.neuronforge.quotes.models.Quote

object DataManager {

    var data = emptyArray<Quote>()
    var currentQuote: Quote? = null
    var currentPage = mutableStateOf(Pages.LISTING)
    var isDataLoaded = mutableStateOf(false)


    fun loadAssetsFromFile(context: Context) {
        val inputStream = context.assets.open("quote.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        data = gson.fromJson(json, Array<Quote>::class.java)
        isDataLoaded.value = true
    }

    fun switchPages(quote: Quote?) {
        if (currentPage.value == Pages.LISTING) {
            currentQuote = quote
            currentPage.value = Pages.DETAILS
        } else {
            currentPage.value = Pages.LISTING
        }
    }
}