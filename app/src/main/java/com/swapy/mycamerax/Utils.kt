package com.swapy.mycamerax

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    private const val TAG = "Utils"
    const val IMAGE_PATH = "imgPath"
    private const val SIMPLE_DATE_FORMAT = "dd-MM-yyyy HH:mm aa"

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

    fun getCurrentTimeStamp(): String {
        return SimpleDateFormat(
            SIMPLE_DATE_FORMAT,
            Locale.US
        ).format(System.currentTimeMillis())
    }

}