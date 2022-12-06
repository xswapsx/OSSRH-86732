package com.swapy.mycamerax

import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.Paint.DITHER_FLAG
import androidx.annotation.ColorInt

object Watermark {


    fun addWatermark(
        bitmap: Bitmap,
        txtDate: String, txtLat: String, txtLong: String, txtHouseId: String
    ): Bitmap {
        val options = WatermarkOptions()
        val result = bitmap.copy(bitmap.config, true)
        val canvas = Canvas(result)
        val paint = Paint(ANTI_ALIAS_FLAG or DITHER_FLAG)
        paint.textAlign = when (options.corner) {
            Corner.TOP_LEFT,
            Corner.BOTTOM_LEFT -> Paint.Align.LEFT
            Corner.TOP_RIGHT,
            Corner.BOTTOM_RIGHT -> Paint.Align.RIGHT
        }
        val textSize = result.width * options.textSizeToWidthRatio
        paint.textSize = textSize
        paint.color = options.textColor
        if (options.shadowColor != null) {
            paint.setShadowLayer(textSize / 2, 0f, 0f, options.shadowColor)
        }
        if (options.typeface != null) {
            paint.typeface = options.typeface
        }
        val padding = result.width * options.paddingToWidthRatio
        val coordinates =
            calculateCoordinates(
                txtDate,
                paint,
                options,
                canvas.width,
                canvas.height,
                padding
            )
        canvas.drawText(txtDate, coordinates.x, coordinates.y, paint)     //date
        canvas.drawText("LONG: $txtLong", coordinates.x, coordinates.y - 25, paint)       //long
        canvas.drawText("LAT: $txtLat", coordinates.x, coordinates.y - 50, paint)    //lat
        canvas.drawText("ID: $txtHouseId", coordinates.x, coordinates.y - 75, paint)      //houseId
        return result
    }

    private fun calculateCoordinates(
        watermarkText: String,
        paint: Paint,
        options: WatermarkOptions,
        width: Int,
        height: Int,
        padding: Float
    ): PointF {
        val x = when (options.corner) {
            Corner.TOP_LEFT,
            Corner.BOTTOM_LEFT -> {
                padding
            }
            Corner.TOP_RIGHT,
            Corner.BOTTOM_RIGHT -> {
                width - padding
            }
        }
        val y = when (options.corner) {
            Corner.BOTTOM_LEFT,
            Corner.BOTTOM_RIGHT -> {
                height - padding
            }
            Corner.TOP_LEFT,
            Corner.TOP_RIGHT -> {
                val bounds = Rect()
                paint.getTextBounds(watermarkText, 0, watermarkText.length, bounds)
                val textHeight = bounds.height()
                textHeight + padding

            }
        }
        return PointF(x, y)
    }

    enum class Corner {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
    }

    data class WatermarkOptions(
        val corner: Corner = Corner.BOTTOM_LEFT,
        val textSizeToWidthRatio: Float = 0.04f,
        val paddingToWidthRatio: Float = 0.03f,
        @ColorInt val textColor: Int = Color.WHITE,
        @ColorInt val shadowColor: Int? = Color.BLACK,
        val typeface: Typeface? = Typeface.DEFAULT_BOLD
    )

}