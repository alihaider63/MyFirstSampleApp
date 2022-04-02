package com.example.myfirstsampleapp

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstsampleapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_second)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val imageReceived: Bitmap? = intent.getParcelableExtra("bitmap")
        val image = intent.getIntExtra("drawable", R.drawable.butterfly1)
        if (image != R.drawable.back) {
            binding.myImage.setImageResource(image)
        } else {
            imageReceived?.let {
                binding.myImage.setImageBitmap(it)
            }
        }
    }
}