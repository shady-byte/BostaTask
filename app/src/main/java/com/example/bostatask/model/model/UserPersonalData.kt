package com.example.bostatask.model.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserPersonalData(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("username")
    val userName: String?,

    @SerializedName("email")
    val email: String,

    @SerializedName("address")
    var address: UserAddress,
) : Serializable
