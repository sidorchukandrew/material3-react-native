package com.material3.reactnative

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.divider.MaterialDivider

class DividerComponent : MaterialDivider {
  constructor(context: Context) : super(context)

  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
    context, attrs, defStyleAttr
  )

  fun setColor(value: String?) {
    val color = value?.let { android.graphics.Color.parseColor(it) }
    if (color != null)  dividerColor = color
  }
}
