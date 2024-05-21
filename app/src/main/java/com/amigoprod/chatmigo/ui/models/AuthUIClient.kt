package com.amigoprod.chatmigo.ui.models

import android.app.Activity
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.amigoprod.chatmigo.SignInResult
import com.amigoprod.chatmigo.User
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import java.util.concurrent.TimeUnit


class AuthUIClient(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val auth = Firebase.auth
    private lateinit var _verificationID : String

    val isOtpSent = savedStateHandle.getStateFlow("isOtpSent", false)


    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            Log.d("auth", "Verification Completed with credential $p0")
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            Log.d("auth", "Verification failed with exception $p0")
        }

        override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
            Log.d("auth", "Code sent with verification ID: $p0")
            _verificationID = p0
            savedStateHandle["isOtpSent"] = true
        }
    }

    suspend fun signInWithPhoneAuthCredentials(
        name: String,
        verificationCode: String
    ): SignInResult {
        val credential = PhoneAuthProvider.getCredential(_verificationID, verificationCode)
        return try {
            val authResult = auth.signInWithCredential(credential).await()
            SignInResult(
                data = authResult.user?.run {
                    User(
                        uid = uid,
                        name = name,
                        phone = phoneNumber
                    )
                },
                errorMsg = null
            )
        } catch (e: Exception){
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMsg = e.message
            )
        }
    }

    fun sendVerificationCode(
        number: String,
        activity: Activity
    ) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(number)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun getSignedInUser() : User? = auth.currentUser?.run {
        phoneNumber?.let {
            User(
                uid = uid,
                name = displayName,
                phone = it
            )
        }
    }

    fun signOut() {
        try {
            auth.signOut()
            Log.d("user", "Signed out...")
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }
}