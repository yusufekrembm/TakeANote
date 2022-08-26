package com.yusufekrem.takeanote.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yusufekrem.takeanote.databinding.ActivityNotePageBinding


class NotePageActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNotePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotePageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}