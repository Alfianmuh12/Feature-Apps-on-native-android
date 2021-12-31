package com.aplikasi.uas_rpl_android

import android.app.AlertDialog
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.face.FaceDetector
import java.io.FileDescriptor
import java.io.IOException

class FaceDetectionAccess : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_face_detection_access)
        val btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "image/*"
            startActivityForResult(intent, READ_REQUEST_CODE)
        }
    }

    public override fun onActivityResult(
        requestCode: Int, resultCode: Int,
        resultData: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (requestCode == READ_REQUEST_CODE && resultCode == RESULT_OK) {
            var uri: Uri? = null
            if (resultData != null) {
                uri = resultData.data
                Log.i(TAG, "Uri: " + uri.toString())
                try {
                    getBitmapFromUri(uri)
                } catch (e: IOException) {
//                    e.printStackTrace();
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun getBitmapFromUri(uri: Uri?) {
        val parcelFileDescriptor = contentResolver.openFileDescriptor(
            uri!!, "r"
        )
        val fileDescriptor = parcelFileDescriptor!!.fileDescriptor
        showImage(fileDescriptor)
    }

    fun showImage(fileDescriptor: FileDescriptor?) {
        //Load the Image
        val myImageView = findViewById<ImageView>(R.id.imgview)
        val options = BitmapFactory.Options()
        options.inMutable = true

        //create paint object to draw square
        val myBitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        val myRectPaint = Paint()
        myRectPaint.strokeWidth = 5f
        myRectPaint.color = Color.RED
        myRectPaint.style = Paint.Style.STROKE

        //create canvas to draw on
        val tempBitmap = Bitmap.createBitmap(myBitmap.width, myBitmap.height, Bitmap.Config.RGB_565)
        val tempCanvas = Canvas(tempBitmap)
        tempCanvas.drawBitmap(myBitmap, 0f, 0f, null)

        //create face detector
        val faceDetector = FaceDetector.Builder(
            applicationContext
        ).setTrackingEnabled(false)
            .build()
        if (!faceDetector.isOperational) {
            AlertDialog.Builder(applicationContext)
                .setMessage("Could not set up the face detector!").show()
            return
        }

        //detect faces
        val frame = Frame.Builder().setBitmap(myBitmap).build()
        val faces = faceDetector.detect(frame)
        for (i in 0 until faces.size()) {
            val thisFace = faces.valueAt(i)
            val x1 = thisFace.position.x
            val y1 = thisFace.position.y
            val x2 = x1 + thisFace.width
            val y2 = y1 + thisFace.height
            tempCanvas.drawRoundRect(RectF(x1, y1, x2, y2), 2f, 2f, myRectPaint)
        }
        myImageView.setImageDrawable(BitmapDrawable(resources, tempBitmap))
    }

    companion object {
        private const val READ_REQUEST_CODE = 42
        private const val TAG = "MainActivity"
    }
}