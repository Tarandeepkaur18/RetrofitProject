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

class MainActivity3 : AppCompatActivity() {
    lateinit var retrofit: retrofit
    lateinit var postsAdapter: PostAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        recyclerView = findViewById(R.id.rv)
        progressBar = findViewById(R.id.progressBar)
        retrofit = retrofit()
        postsAdapter = PostAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = postsAdapter

    }

    fun getPosts(view: View) {
        progressBar.visibility = View.VISIBLE
        postsAdapter.clearList()
        retrofit.retrofitInterface.getPosts().enqueue(object : Callback<PostsResponse> {
            override fun onResponse(call: Call<PostsResponse>, response: Response<PostsResponse>) {
                Log.e("TAG", "response ${response.body()}")
                response.body()?.let { postsAdapter.updateList(it) }
                progressBar.visibility = View.GONE

            }

            override fun onFailure(call: Call<PostsResponse>, t: Throwable) {
                Log.e("TAG", "response ${t}")
                Toast.makeText(this@MainActivity3, t.toString(), Toast.LENGTH_LONG).show()
                progressBar.visibility = View.GONE
            }
        })
    }


}
