package com.example.farmfirst

import android.net.Uri
import android.os.Bundle
import android.widget.SearchView
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
    var str: String? = null
    var b1: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGovtschemesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //val dataModelList = DataModel.genDataModels(20)
        fetchData()
        myadapter = MyAdapter( this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter= myadapter
        //mySearchView = findViewById(R.id.filter);
        val search = findViewById<SearchView>(R.id.filter)
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                b1=false
                if(query=="")
                {
                    b1=true
                }
                str = "+\"$query\""
                fetchData()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                str = "+\"$newText\""
                if(newText=="")
                {
                    b1=true
                }
                fetchData()
                return true
            }

        })
    }

    private fun fetchData() {
        val url = "https://newsapi.org/v2/everything?q=\"agriculture\"+\"farmer\"+\"india\"+\"scheme\"+\"welfare\"-UPSC$str&apiKey=c2703a6484d640bfa98398c825826061"
        val jsonObjectRequest = object: JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                val newsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<News>()

                val news1 = News(
                    "Pradhan Mantri Fasal Bima Yojana (PMFBY)", "FarmFirst", "https://pmfby.gov.in/",
                    "https://www.india.gov.in/sites/upload_files/npi/files/spotlights/fasal-bima-yojna-inner.jpg",
                    "The extreme weather conditions cause crop failures and financial loss to the farmers. So, " +
                            "to save them from the ill effects, the Govt, by merging all " +
                            "previous crop insurance schemes, launched Pradhan Mantri Fasal Bima Yojana " +
                            "(PMFBY) from Kharif 2016 season with the aim to support production in agriculture " +
                            "by providing an affordable crop insurance system. Central government has made" +
                            " Aadhaar mandatory for availing crop insurance from Kharif 2017 season. " +
                            "The scheme has been restructured with states given option to determine " +
                            "scale of finance at district level as per average yield."
                )
                val news2 = News(
                    "Pradhan Mantri Krishi Sinchai Yojana (PMKSY)", "FarmFirst", "https://pmksy.gov.in/",
                    "https://pmksy.gov.in/images/Banner3.jpg", "The Government of India" +
                            " has structured Pradhan Mantri Krishi Sinchayee Yojana (PMKSY) with the vision" +
                            " to extend the coverage of irrigation and improving water use efficiency " +
                            "in a focused manner. PMKSY focuses end to end solution on source creation," +
                            " distribution, management, field application and extension activities." +
                            " The Centre has structured the scheme with merging previous irrigation " +
                            "and water management programmes such as (AIBP), (IWMP) and (OFWM) scheme. " +
                            "The Centre has approved Rs. 50,000 crore for five years for the implementation" +
                            " of the flagship irrigation scheme across India."
                )
                val news3 = News(
                    "Kisan Credit Card (KCC)", "FarmFirst", "https://www.zeebiz.com/" +
                            "personal-finance/news-pm-kisan-what-is-kisan-credit-card-how-to-apply-know-step-" +
                            "by-step-process-to-apply-kcc-online-through-sbi-162498", "https:" +
                            "//kj1bcdn.b-cdn.net/media/42201/kisan-credit-card1-1.jpg", "The Kisan" +
                            " Credit Card (KCC) scheme is a Central scheme that provides farmers with" +
                            " timely access to credit. The scheme was launched in 1998 with an aim to" +
                            " provide short-term formal credit to farmers. It was created by the National" +
                            " Bank for Agriculture and Rural Development (NABARD). The PM Kisan Credit" +
                            " Cards have now been linked to the Pradhan Mantri Kisan Samman Nidhi Yojana " +
                            "(PM Kisan). Farmers can seek a loan from KCC for up to Rs 3 lakh at 4 per" +
                            " cent interest rate. It is also easier for PM Kisan beneficiaries to apply" +
                            " for KCC."
                )
                if(b1) {
                    newsArray.add(news1)
                    newsArray.add(news2)
                    newsArray.add(news3)
                }
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
                if(!b1) {
                    newsArray.add(news1)
                    newsArray.add(news2)
                    newsArray.add(news3)
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