package com.amigoprod.chatmigo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Message(
    @SerializedName("id") @Expose var id: Int? = null,
)