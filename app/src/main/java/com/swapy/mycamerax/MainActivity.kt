package com.swapy.mycamerax

import android.app.Activity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.swapy.mycamerax.Utils.IMAGE_PATH
import com.swapy.mycamerax.Utils.toast
import com.swapy.mycamerax.databinding.ActivityMainBinding
import java.io.File

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
                // Do something here
                toast("Path: ${result.data?.getStringExtra(IMAGE_PATH)}")
            }

//            deleteTheFile(result.data?.getStringExtra(IMAGE_PATH).toString())
        }

    private fun deleteTheFile(path: String) {
        val fDelete = File(path)
        if (fDelete.exists()) {
            if (fDelete.delete())
                toast("file deleted: $path")
            else
                toast("file is not deleted!")
        }
    }

}