package com.example.greetingcard

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.ComponentActivity


class CaptureImageActivity : ComponentActivity() {

    private lateinit var cameraOpenId: Button
    private lateinit var returnToMainId: Button
    lateinit var clickImageId: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //use the defined layout
        setContentView(R.layout.activity_imageview)

            //get each component by id
            cameraOpenId = findViewById(R.id.camera_button)
            clickImageId = findViewById(R.id.click_image)
            returnToMainId = findViewById(R.id.return_to_main)

            //camera button intent starter
            cameraOpenId.setOnClickListener(View.OnClickListener { v: View? ->
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, pic_id)
            })

            //return to main button listener
            returnToMainId.setOnClickListener(View.OnClickListener { v: View? ->
                val mainActivityIntent = Intent(this,  MainActivity::class.java )
                startActivity(mainActivityIntent)
            })
        }

    //retrieve image from camera activity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            // Match the request 'pic id with requestCode
            if (requestCode == pic_id) {
                val photo = data!!.extras!!["data"] as Bitmap?
                clickImageId.setImageBitmap(photo)
            }
        }

        companion object {
            // Define the pic id
            private const val pic_id = 123
        }
    }
