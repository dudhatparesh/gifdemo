package com.testing.gifdemo

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

const val INITIAL_LOOP_COUNT = 1 //has to be minimum 1 other wise it will continue
const val CLICK_LOOP_COUNT = 5
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageView = findViewById<ImageView>(R.id.imageView)

        Glide.with(this).asGif().load("https://i.imgur.com/1ALnB2s.gif")
            .listener(object : RequestListener<GifDrawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: GifDrawable?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    resource?.setLoopCount(INITIAL_LOOP_COUNT)
                    resource?.registerAnimationCallback(object :
                        Animatable2Compat.AnimationCallback() {
                        override fun onAnimationEnd(drawable: Drawable) {
                            if(drawable is GifDrawable){
                                drawable.stop()
                            }
                        }
                    })
                    return false
                }

            })
            .into(imageView)

        imageView.setOnClickListener {
            if (imageView.drawable is GifDrawable) {
                val drawable = imageView.drawable as GifDrawable
                if (drawable.isRunning) {
                    drawable.stop()
                } else {
                    drawable.setLoopCount(CLICK_LOOP_COUNT)
                    drawable.start()
                }

            }
        }

    }
}