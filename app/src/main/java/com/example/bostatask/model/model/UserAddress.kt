package com.example.bostatask.model.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserAddress(
    @SerializedName("street")
    val street: String,

    @SerializedName("suite")
    val suite: String,

    @SerializedName("city")
    val city: String,

    @SerializedName("zipcode")
    val zipCode: String?
) : Serializable
