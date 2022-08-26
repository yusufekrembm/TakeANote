package com.yusufekrem.takeanote.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.yusufekrem.takeanote.R
import com.yusufekrem.takeanote.databinding.ActivityForgotPassBinding
import com.yusufekrem.takeanote.databinding.ActivityMainBinding

class ForgotPassActivity : AppCompatActivity() {

    private lateinit var binding : ActivityForgotPassBinding
    private lateinit var toolbar : Toolbar
    private lateinit var userEmail : EditText
    private lateinit var userPass : Button
    private lateinit var progressBar : ProgressBar

    private lateinit var  firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPassBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        toolbar = findViewById(R.id.toolbar)
        userEmail = findViewById(R.id.etUserForgotPass)
        userPass = findViewById(R.id.btnForgotPassword)
        progressBar = findViewById(R.id.progressBar)
        firebaseAuth = FirebaseAuth.getInstance()

        toolbar.title = "Forgot Password"

        userPass.setOnClickListener{
            progressBar.visibility = View.VISIBLE
            firebaseAuth.sendPasswordResetEmail(userEmail.text.toString()).addOnCompleteListener {
                progressBar.visibility = View.GONE
                if(it.isSuccessful){
                    Toast.makeText(this,"Password send to your email", Toast.LENGTH_LONG).show()
                    val intent = Intent(this,SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                } else{
                    Toast.makeText(this,it.exception?.localizedMessage,Toast.LENGTH_LONG).show()
                }
            }
        }


    }
}