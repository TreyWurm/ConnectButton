package com.appmea.connectbutton

import android.graphics.Paint
import android.view.View
import timber.log.Timber


fun measureDimension(desiredSize: Int, measureSpec: Int): Int {
    var result: Int
    val specMode = View.MeasureSpec.getMode(measureSpec)
    val specSize = View.MeasureSpec.getSize(measureSpec)
    if (specMode == View.MeasureSpec.EXACTLY) {
        result = specSize
    } else {
        result = desiredSize
        if (specMode == View.MeasureSpec.AT_MOST) {
            result = result.coerceAtMost(specSize)
        }
    }
    if (result < desiredSize) {
        Timber.e("The view is too small, the content might get cut")
    }
    return result
}

fun measureMaxTextHeight(textPaint: Paint): Float {
    val fm: Paint.FontMetrics = textPaint.fontMetrics
    return fm.descent - fm.ascent
}