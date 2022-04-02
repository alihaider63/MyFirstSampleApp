package com.example.myfirstsampleapp

import android.graphics.Bitmap

data class Butterfly(
    var name: String = "Ali",
    var family: String = "Haider",
    var butterfly: Int? = null,
    var image: Bitmap? = null,
    var count: Int = 0
)