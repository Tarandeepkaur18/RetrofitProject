package com.taran.retrofitproject


import com.google.gson.annotations.SerializedName

class PostsResponse : ArrayList<PostsResponseItem>()
data class PostsResponseItem(
    @SerializedName("body")
    val body: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("post_id")
    val postId: Int? = null
)