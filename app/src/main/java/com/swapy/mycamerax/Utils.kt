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
    const val SIMPLE_DATE_FORMAT = "dd-MM-yyyy hh:mm aa"
    const val FILENAME_FORMAT = "yyyy-MM-dd'T'HHmmssSSS"

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

    fun getDateTime(dFormat: String): String {
        return SimpleDateFormat(
            dFormat,
            Locale.US
        ).format(System.currentTimeMillis())
    }

}