package com.example.farmfirst

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.farmfirst.databinding.ActivityGovtschemesBinding
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.MutableMap
import kotlin.collections.set

class Govtschemes : AppCompatActivity(), SchemeItemClicked {

    private lateinit var myadapter: MyAdapter
    private lateinit var binding: ActivityGovtschemesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGovtschemesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //val dataModelList = DataModel.genDataModels(20)
        fetchData()
        myadapter = MyAdapter( this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter= myadapter
    }

    private fun fetchData() {
        val url = "https://newsapi.org/v2/everything?q=\"agriculture\"+\"farmer\"+\"india\"+\"scheme\"+\"welfare\"-UPSC&apiKey=c2703a6484d640bfa98398c825826061"
        val jsonObjectRequest = object: JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                val newsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<News>()
                for(i in 0 until newsJsonArray.length()) {
                    val newsJSONObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJSONObject.getString("title"),
                        newsJSONObject.getString("author"),
                        newsJSONObject.getString("url"),
                        newsJSONObject.getString("urlToImage"),
                        newsJSONObject.getString("content")
                    )
                    newsArray.add(news)
                }
                myadapter.updateNews(newsArray)
            },
            Response.ErrorListener {

            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: News) {
        //Toast.makeText(this,"clicked item is ${item.title}", Toast.LENGTH_LONG).show()
        val builder =  CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }
}
