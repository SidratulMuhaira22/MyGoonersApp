package com.example.mygoonersaap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mygoonersaap.databinding.ActivityDetailPlayerBinding

class DetailActivityPlayer : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val dataPlayer = intent.getParcelableExtra("key_player") as? Player

        if (dataPlayer != null) {
            binding.titleDetail.text = dataPlayer.name
            binding.descriptionDetail.text = dataPlayer.description
            binding.imageDetail.setImageResource(dataPlayer.photo)
        }
        binding.actionShare.setOnClickListener {
            val message = "Arsenal Player : ${dataPlayer?.name}"
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.putExtra(Intent.EXTRA_TEXT, message)
            shareIntent.type = "text/plain"

            val shareIntentChooser = Intent.createChooser(shareIntent, "Share via")
            startActivity(shareIntentChooser)
        }
    }
}