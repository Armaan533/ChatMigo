package com.amigoprod.chatmigo.auth

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.amigoprod.chatmigo.User
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.auth
import java.util.concurrent.CancellationException
import java.util.concurrent.TimeUnit


class AuthUIClient {
    private val auth = Firebase.auth
    private lateinit var _verificationID : String

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            Log.d("auth", "Verification Completed with credential $p0")
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            Log.d("auth", "Verification failed with exception $p0")
        }

        override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
            Log.d("auth", "Code sent")
            _verificationID = p0
        }
    }

    fun signInWithPhoneAuthCredentials(
        verificationCode: String,
        context: Context
    ) {
        val credential = PhoneAuthProvider.getCredential(_verificationID, verificationCode)
        auth.signInWithCredential(credential).addOnCompleteListener(context as Activity){task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Verification successful..", Toast.LENGTH_SHORT).show()
            } else {
                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(
                        context,
                        "Verification failed.." + (task.exception as FirebaseAuthInvalidCredentialsException).message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
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
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }
}