package com.example.farmfirst

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_govtschemes.*
import java.util.ArrayList
import org.json.JSONObject
import com.android.volley.toolbox.JsonObjectRequest
import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.Volley

class Govtschemes : AppCompatActivity(), SchemeItemClicked {

    private lateinit var myadapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_govtschemes)
        //val dataModelList = DataModel.genDataModels(20)
        fetchData()
        myadapter = MyAdapter( this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = myadapter
    }

    private fun fetchData() {
        val url = "https://newsapi.org/v2/everything?q=agriculture+farmer+india+scheme&apiKey=c2703a6484d640bfa98398c825826061"
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
