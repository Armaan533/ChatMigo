package com.amigoprod.chatmigo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val uid: String? = null,
    val name: String? = null,
    val phone: String? = null
//    val password: String? = null
) : Parcelable