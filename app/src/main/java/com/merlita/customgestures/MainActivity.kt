package com.merlita.customgestures

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.merlita.customgestures.databinding.ActivityMainBinding
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var scaleGestureDetector: ScaleGestureDetector? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        scaleGestureDetector = ScaleGestureDetector(this,
            MyOnScaleGestureListener())


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleGestureDetector?.onTouchEvent(event)
        return true
    }
    inner class MyOnScaleGestureListener : SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            val scaleFactor = detector.scaleFactor
            if (scaleFactor > 1) {
                binding.myTextView.text = "Zooming Out"
            } else {
                binding.myTextView.text = "Zooming In"
            }
            return true
        }
        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            return true
        }
        override fun onScaleEnd(detector: ScaleGestureDetector) {
        }
    }
}