package com.swapy.mycamerax

import android.app.Activity
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.swapy.mycamerax.Utils.IMAGE_PATH
import com.swapy.mycamerax.Utils.deleteTheFile
import com.swapy.mycamerax.Utils.toast
import com.swapy.mycamerax.Watermark.addWatermark
import com.swapy.mycamerax.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOpenCam.setOnClickListener {
            openCameraActivity.launch(CameraActivity.getIntent(this, 1))
        }
    }

    private val openCameraActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                filePath = result.data?.getStringExtra(IMAGE_PATH).toString()
                toast("Path: ${result.data?.getStringExtra(IMAGE_PATH)}")

                val bitmap = BitmapFactory.decodeFile(filePath)
                Watermark.WatermarkOptions()
                val newBitmap = addWatermark(
                    bitmap, "06-12-2022 04:32 PM",
                    "20.1034293",
                    "78.8089191",
                    "HPSBA1002"
                )
                binding.imgView.setImageBitmap(newBitmap)
                deleteTheFile(filePath)
            }

        }

    companion object {
        private var filePath = ""
    }

}