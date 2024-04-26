package com.amigoprod.chatmigo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

class Message(
    @SerializedName("id") @Expose var id: Int? = null,
    @SerializedName("msg") @Expose var msg: String? = null,
    @SerializedName("timestamp") @Expose var timestamp: Timestamp? = null,
    @SerializedName("delivered") @Expose var delivered: Boolean = false,
    @SerializedName("read") @Expose var read: Boolean = false
)