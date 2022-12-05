package com.swapy.mycamerax

import android.content.Context
import android.widget.Toast

object Utils {
    public const val IMAGE_PATH = "imgPath"
    public fun Context?.toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}