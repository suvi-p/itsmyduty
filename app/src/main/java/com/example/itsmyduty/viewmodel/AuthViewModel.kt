package com.example.itsmyduty.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess: StateFlow<Boolean> = _loginSuccess

    fun login(email: String, password: String) {

        val cleanEmail = email.trim()

        // Email validation
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(cleanEmail).matches()) {
            println("Invalid email format")
            return
        }

        auth.signInWithEmailAndPassword(cleanEmail, password.trim())
            .addOnSuccessListener {

                println("Login Success")
                _loginSuccess.value = true
            }
            .addOnFailureListener { exception ->

                println("Login Failed: ${exception.message}")
            }
    }

    fun register(name: String, email: String, password: String) {

        auth.createUserWithEmailAndPassword(email.trim(), password.trim())
            .addOnSuccessListener { result ->

                val userId = result.user?.uid

                val user = hashMapOf(
                    "id" to userId,
                    "name" to name,
                    "email" to email
                )

                if (userId != null) {
                    FirebaseFirestore.getInstance()
                        .collection("users")
                        .document(userId)
                        .set(user)
                }

                println("Register Success")
            }
            .addOnFailureListener {
                println("Register Failed: ${it.message}")
            }
    }
}
