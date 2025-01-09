package com.material3.reactnative

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

class IconHelper(val context: Context, val name: String) {
  @SuppressLint("DiscouragedApi")
  fun resolve(): Drawable? {
    val resourceId = context.resources.getIdentifier(name, "drawable", context.packageName)
    if (resourceId < 1) return null

    return ContextCompat.getDrawable(context, resourceId)
  }
}
