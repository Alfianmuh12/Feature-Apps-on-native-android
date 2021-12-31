package com.aplikasi.uas_rpl_android
import android.Manifest
import android.content.pm.PackageManager
import android.graphics.SurfaceTexture
import android.hardware.camera2.*
import android.os.Bundle
import android.view.Surface
import android.view.TextureView
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.lang.Exception
import java.util.*


class CameraAccess : AppCompatActivity() {
    private var myCameraCaptureSession: CameraCaptureSession? = null
    private var myCameraID: String? = null
    private var myCameraManager: CameraManager? = null
    private var myCameraDevice: CameraDevice? = null
    private var myTextrureView: TextureView? = null
    private var myCaptureRequestBuilder: CaptureRequest.Builder? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_access)
        myTextrureView = findViewById(R.id.textureView)
        myCameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        openCamera()
    }

    private val myStateCallBack: CameraDevice.StateCallback =
        object : CameraDevice.StateCallback() {
            override fun onOpened(camera: CameraDevice) {
                myCameraDevice = camera
            }

            override fun onDisconnected(camera: CameraDevice) {
                myCameraDevice!!.close()
            }

            override fun onError(camera: CameraDevice, error: Int) {
                myCameraDevice!!.close()
                myCameraDevice = null
            }
        }

    private fun openCamera() {
        try {
            var myCameraID = myCameraManager!!.cameraIdList[0]
            ActivityCompat.requestPermissions(
                this@CameraAccess,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PackageManager.PERMISSION_GRANTED
            )
            if (ActivityCompat.checkSelfPermission(
                    this@CameraAccess,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            myCameraManager!!.openCamera(myCameraID, myStateCallBack, null)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun cameraPreview(view: View?) {
        val mySurfaceTexture = myTextrureView!!.surfaceTexture
        val mySurface = Surface(mySurfaceTexture)
        try {
            myCaptureRequestBuilder =
                myCameraDevice!!.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
            myCaptureRequestBuilder!!.addTarget(mySurface)
            myCameraDevice!!.createCaptureSession(
                Arrays.asList(mySurface), object : CameraCaptureSession.StateCallback() {
                    override fun onConfigured(session: CameraCaptureSession) {
                        myCameraCaptureSession = session
                        myCaptureRequestBuilder!!.set(
                            CaptureRequest.CONTROL_MODE,
                            CameraMetadata.CONTROL_MODE_AUTO
                        )
                        try {
                            myCameraCaptureSession!!.setRepeatingRequest(
                                myCaptureRequestBuilder!!.build(),
                                null,
                                null
                            )
                        } catch (e: CameraAccessException) {
                            e.printStackTrace()
                        }
                    }

                    override fun onConfigureFailed(session: CameraCaptureSession) {}
                }, null
            )
        } catch (e: CameraAccessException) {
            e.printStackTrace()


        }
    }


}