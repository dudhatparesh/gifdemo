package com.testing.gifdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageView = findViewById<ImageView>(R.id.imageView)
        Glide.with(this).asGif().load("https://i.imgur.com/1ALnB2s.gif").into(imageView)
        imageView.setOnClickListener {
            if (imageView.drawable is GifDrawable) {
                val drawable = imageView.drawable as GifDrawable
                if (drawable.isRunning) {
                    drawable.stop()
                } else {
                    drawable.start()
                }

            }
        }

    }
}