package com.amigoprod.chatmigo

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
class DataManager(
    private val savedStateHandle: SavedStateHandle,
    private val user: User
) {
    private val rDB = Firebase.database.reference
    private val messages = rDB.child("messages")
    private val fDB = Firebase.firestore
    private val chatRef = fDB.collection("chats/${user.uid}")


    init {
        fetchChats()
    }

    private fun fetchChats(){
        chatRef.whereArrayContains("members", user).get().addOnCompleteListener{ task ->
            if (task.isSuccessful){
                if (!task.result.isEmpty) {
                    savedStateHandle["chats"] = task.result.toObjects(Chat::class.java)
                }
            }
        }
    }

    private fun createChatID(
        member1: User,
        member2: User
    ): String {
        return if (member1.uid?.toInt()!! > member2.uid?.toInt()!!) member2.uid + member1.uid
        else member1.uid + member2.uid
    }

    suspend fun createChat(
        member: User
    ): Chat{
        val cid = createChatID(member1 = member, member2 = user)
        val members = listOf(member, user)
        val chat = Chat(
            cid,
            members = members
        )
        chatRef.document(cid).set(chat).await()
        return chat
    }

    suspend fun sendMessage(
        message: Message,
        chat: Chat
    ): Chat {
        val chatMsgs = chat.cid?.let { messages.child(it) }
        chat.lastMessage = message
        chatMsgs?.push()?.setValue(message)?.await()
        return chat
    }

    

}