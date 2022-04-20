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

class MainActivity : AppCompatActivity() {
    lateinit var retrofit: retrofit
    lateinit var usersAdapter: UsersAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rv)
        progressBar = findViewById(R.id.progressBar)
        retrofit = retrofit()
        usersAdapter = UsersAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = usersAdapter

    }

    fun getUsers(view: View) {
        progressBar.visibility = View.VISIBLE
        usersAdapter.clearList()
        retrofit.retrofitInterface.getUsers().enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                Log.e("TAG", "response ${response.body()}")
                response.body()?.let { usersAdapter.updateList(it) }
                progressBar.visibility = View.GONE

            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("TAG", "response ${t}")
                Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_LONG).show()
                progressBar.visibility = View.GONE
            }
        })
    }

    fun getSingleuser(view: View) {
        progressBar.visibility = View.VISIBLE
        usersAdapter.clearList()
        retrofit.retrofitInterface.getSingleUser(100).enqueue(object : Callback<UserResponseItem> {
            override fun onResponse(
                call: Call<UserResponseItem>,
                response: Response<UserResponseItem>
            ) {
                Log.e("TAG", "response ${response.body()}")
                response.body()?.let {
                    var arrayList = ArrayList<UserResponseItem>()
                    arrayList.add(it)
                    usersAdapter.updateList(arrayList)
                }
                progressBar.visibility = View.GONE

            }

            override fun onFailure(call: Call<UserResponseItem>, t: Throwable) {
                Log.e("TAG", "response ${t}")
                Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_LONG).show()
                progressBar.visibility = View.GONE
            }
        })
    }
}