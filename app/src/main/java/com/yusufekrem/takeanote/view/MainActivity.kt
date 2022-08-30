package com.yusufekrem.takeanote.view

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.yusufekrem.takeanote.databinding.ActivityMainBinding
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor :SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
        editor = sharedPreferences.edit()

        logOut()
    }


    private fun logOut(){
        binding.logoutButton.setOnClickListener{
            checkPositiveButton()
        }
    }

    private fun checkPositiveButton(){
        val mBuilder = AlertDialog.Builder(this)
            .setTitle("Confirm")
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("Yes", null)
            .setNegativeButton("No", null)
            .show()
       // Function for the positive button
        // is programmed to exit the application
        val mPositiveButton = mBuilder.getButton(AlertDialog.BUTTON_POSITIVE)
        mPositiveButton.setOnClickListener {
            finish()
        }
    }

}