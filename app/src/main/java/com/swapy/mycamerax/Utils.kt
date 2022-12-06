package com.swapy.mycamerax

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import java.io.File

object Utils {
    const val IMAGE_PATH = "imgPath"
    private const val TAG = "Utils"
    fun Context?.toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun deleteTheFile(path: String) {
        val fDelete = File(path)
        if (fDelete.exists()) {
            if (fDelete.delete()) {
                Log.e(TAG, "file deleted!")
            } else {
                Log.e(TAG, "file is not deleted!")
            }

        }
    }

}