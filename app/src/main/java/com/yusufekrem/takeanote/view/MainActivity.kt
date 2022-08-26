package com.yusufekrem.takeanote.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yusufekrem.takeanote.R
import com.yusufekrem.takeanote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.logoutButton.setOnClickListener{
            val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.imageButton.setOnClickListener{
            val intent= Intent(this,NotePageActivity::class.java)
            startActivity(intent)
            finish()
        }
    }



}