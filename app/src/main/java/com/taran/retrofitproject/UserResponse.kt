package com.taran.retrofitproject


import com.google.gson.annotations.SerializedName

class UserResponse : ArrayList<UserResponseItem>()

data class UserResponseItem(
        @SerializedName("email")
        val email: String? = null,
        @SerializedName("gender")
        val gender: String? = null,
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("status")
        val status: String? = null
    )
