package com.appmea.connectbutton

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import kotlin.math.ceil


private const val DEFAULT_CORNER_RADIUS = 16f

class ConnectButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {


    /*
     *
     */
    var cornerRadius: Float = DEFAULT_CORNER_RADIUS
    private val minPaddingHorizontal = 0.dpAsPx
    private val minPaddingVertical = 0.dpAsPx
    private val textConnect = context.getString(R.string.text_connect)
    val paintText = TextPaint().apply {
        color = Color.BLACK
        isAntiAlias = true
        textAlignment = TEXT_ALIGNMENT_CENTER
        textSize = 16.spAsPxFloat
        textAlign = Paint.Align.LEFT
    }
    private var staticLayout = newStaticLayout(paintText.measureText(textConnect).toInt())

    /*
     * Positioning + Sizes
     */
    private var textWidth = ceil(paintText.measureText(textConnect)).toInt()
    private var textHeight = ceil(measureMaxTextHeight(paintText)).toInt()
    private var deltaY: Float = 0f


    init {
        setBackgroundColor(Color.CYAN)
        setPadding(
            paddingLeft.coerceAtLeast(minPaddingHorizontal),
            paddingTop.coerceAtLeast(minPaddingVertical),
            paddingEnd.coerceAtLeast(minPaddingHorizontal),
            paddingBottom.coerceAtLeast(minPaddingVertical)
        )
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = staticLayout.width + paddingLeft + paddingRight
        val width = measureDimension(desiredWidth, widthMeasureSpec)

        val desiredHeight = staticLayout.height + paddingTop + paddingBottom
        val height = measureDimension(desiredHeight, heightMeasureSpec)

        updateStaticLayout(width, height)
        deltaY = (height - staticLayout.height) / 2f
        setMeasuredDimension(width, height)
    }


    override fun onDraw(canvas: Canvas) {
        canvas.save()
        canvas.translate(paddingLeft.toFloat(), paddingTop.toFloat() + deltaY)
        staticLayout.draw(canvas)
        canvas.restore()
    }


    private fun updateStaticLayout(width: Int, height: Int) {
        if (width != staticLayout.width || height != staticLayout.height) {
            staticLayout = newStaticLayout(width)
        }
    }

    private fun newStaticLayout(width: Int): StaticLayout {
        return StaticLayout(textConnect, paintText, width, Layout.Alignment.ALIGN_CENTER, 1.0f, 0f, true)
    }
}