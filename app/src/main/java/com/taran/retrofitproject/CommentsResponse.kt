package com.taran.retrofitproject


import com.google.gson.annotations.SerializedName

class CommentsResponse : ArrayList<CommentsResponseItem>()
data class CommentsResponseItem(
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