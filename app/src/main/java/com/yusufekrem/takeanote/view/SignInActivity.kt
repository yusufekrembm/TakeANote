package com.yusufekrem.takeanote.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.yusufekrem.takeanote.R
import com.yusufekrem.takeanote.databinding.ActivitySignInBinding


class SignInActivity : AppCompatActivity()  {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var username : String
    private lateinit var password : String
    private lateinit var editTextUserName : TextInputEditText
    private lateinit var editTextPassword : TextInputEditText
    private lateinit var saveLoginCheckBox: CheckBox
    private  lateinit var loginPreferences: SharedPreferences
    private lateinit var  loginPrefsEditor: SharedPreferences.Editor
    private lateinit var keepMeInside : CheckBox
    private lateinit var settings : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        //Declaring some Items
        editTextUserName = findViewById(R.id.emailEt)
        editTextPassword =  findViewById(R.id.passET)
        saveLoginCheckBox = findViewById(R.id.rememberMe)
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        keepMeInside = findViewById(R.id.keepMeLogged)
        settings = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
        editor = settings.edit()


        keepMeInside.setOnCheckedChangeListener { _, isChecked ->
            val settings = getSharedPreferences("PREFS_NAME", MODE_PRIVATE)
            val editor = settings.edit()
            editor.putBoolean("isChecked", isChecked)
            editor.commit()
        }


        // Functions
        checkKeepMeInside()
        forgotPass()
        signOutButton()
        signInButton()
        checkSaveLogin()
    }





    private fun forgotPass(){
        binding.textView3.setOnClickListener {
            val intent = Intent(this,ForgotPassActivity::class.java)
            startActivity(intent)
        }
    }
    private fun signOutButton(){
        binding.textView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
    private fun signInButton(){
        binding.signInButton.setOnClickListener {
            username = editTextUserName.text.toString()
            password = editTextPassword.text.toString()

            if (saveLoginCheckBox.isChecked) {
                loginPrefsEditor.putBoolean("saveLogin", true);
                loginPrefsEditor.putString("username", username);
                loginPrefsEditor.putString("password", password);
                loginPrefsEditor.commit();
            } else {
                loginPrefsEditor.clear();
                loginPrefsEditor.commit();
            }

            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                     goToMainActivity()
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }

        }
    }

    private fun checkSaveLogin(){
        var saveLogin:Boolean = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin) {
            editTextUserName.setText(loginPreferences.getString("username", ""));
            editTextPassword.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.isChecked = true;
        }
    }

    private fun goToMainActivity(){
        var intent = Intent(this, MainActivity::class.java)
       startActivity(intent)
    }

    private fun checkKeepMeInside(){
        var isChecked = false
        val settings1 = getSharedPreferences("PREFS_NAME", 0)
        isChecked = settings1.getBoolean("isChecked", false)

        if (isChecked) {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        } else {

        }
    }
}