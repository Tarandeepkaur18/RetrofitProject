package com.taran.retrofitproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    lateinit var retrofit: retrofit
    lateinit var commentsAdapter: CommentAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rv)
        progressBar = findViewById(R.id.progressBar)
        retrofit = retrofit()
        commentsAdapter = CommentAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = commentsAdapter

    }

    fun getComments(view: View) {
        progressBar.visibility = View.VISIBLE
        commentsAdapter.clearList()
        retrofit.retrofitInterface.getComments().enqueue(object : Callback<CommentsResponse> {
            override fun onResponse(call: Call<CommentsResponse>, response: Response<CommentsResponse>) {
                Log.e("TAG", "response ${response.body()}")
                response.body()?.let { commentsAdapter.updateList(it) }
                progressBar.visibility = View.GONE

            }

            override fun onFailure(call: Call<CommentsResponse>, t: Throwable) {
                Log.e("TAG", "response ${t}")
                Toast.makeText(this@MainActivity2, t.toString(), Toast.LENGTH_LONG).show()
                progressBar.visibility = View.GONE
            }
        })
    }

    fun getSingleComments(view: View) {
        progressBar.visibility = View.VISIBLE
        commentsAdapter.clearList()
        retrofit.retrofitInterface.getSingleComments(100).enqueue(object : Callback<CommentsResponseItem> {
            override fun onResponse(
                call: Call<CommentsResponseItem>,
                response: Response<CommentsResponseItem>
            ) {
                Log.e("TAG", "response ${response.body()}")
                response.body()?.let {
                    var arrayList = ArrayList<CommentsResponseItem>()
                    arrayList.add(it)
                    commentsAdapter.updateList(arrayList)
                }
                progressBar.visibility = View.GONE

            }

            override fun onFailure(call: Call<CommentsResponseItem>, t: Throwable) {
                Log.e("TAG", "response ${t}")
                Toast.makeText(this@MainActivity2, t.toString(), Toast.LENGTH_LONG).show()
                progressBar.visibility = View.GONE
            }
        })
    }
}
