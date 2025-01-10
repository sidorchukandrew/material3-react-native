package com.material3.reactnative

import android.content.Context
import android.content.res.Configuration
import android.view.ContextThemeWrapper
import androidx.annotation.AttrRes
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.WritableMap
import com.facebook.react.bridge.WritableNativeMap
import com.google.android.material.color.DynamicColors

class ColorsModule(reactContext: ReactApplicationContext) : NativeColorsSpec(reactContext) {
  private var isDarkMode: Boolean = false
  private var themedContext: Context? = null

  override fun getName() = NAME

  override fun getColors(): WritableMap {
    determineContext()
    val lightColors = resolveColors()
    switchToDarkMode()
    val darkColors = resolveColors()

    val colors = WritableNativeMap().apply {
      putMap("light", lightColors)
      putMap("dark", darkColors)
    }

    return colors
  }

  companion object {
    const val NAME = "RTNColors"
    var DYNAMIC_COLORS_ENABLED = false
  }

  private fun resolveColors(): WritableMap {
    val colors = WritableNativeMap().apply {
      putString(
        "errorContainer", getColorFromAttr(com.google.android.material.R.attr.colorErrorContainer)
      )
      putString(
        "onBackground", getColorFromAttr(com.google.android.material.R.attr.colorOnBackground)
      )
      putString("onError", getColorFromAttr(com.google.android.material.R.attr.colorOnError))
      putString(
        "onErrorContainer",
        getColorFromAttr(com.google.android.material.R.attr.colorOnErrorContainer)
      )
      putString("onPrimary", getColorFromAttr(com.google.android.material.R.attr.colorOnPrimary))
      putString(
        "onPrimaryContainer",
        getColorFromAttr(com.google.android.material.R.attr.colorOnPrimaryContainer)
      )
      putString(
        "onPrimaryFixed", getColorFromAttr(com.google.android.material.R.attr.colorOnPrimaryFixed)
      )
      putString(
        "onPrimaryFixedVariant",
        getColorFromAttr(com.google.android.material.R.attr.colorOnPrimaryFixedVariant)
      )
      putString(
        "onPrimarySurface",
        getColorFromAttr(com.google.android.material.R.attr.colorOnPrimarySurface)
      )
      putString(
        "onSecondary", getColorFromAttr(com.google.android.material.R.attr.colorOnSecondary)
      )
      putString(
        "onSecondaryContainer",
        getColorFromAttr(com.google.android.material.R.attr.colorOnSecondaryContainer)
      )
      putString(
        "onSecondaryFixed",
        getColorFromAttr(com.google.android.material.R.attr.colorOnSecondaryFixed)
      )
      putString(
        "onSecondaryFixedVariant",
        getColorFromAttr(com.google.android.material.R.attr.colorOnSecondaryFixedVariant)
      )
      putString("onSurface", getColorFromAttr(com.google.android.material.R.attr.colorOnSurface))
      putString(
        "onSurfaceInverse",
        getColorFromAttr(com.google.android.material.R.attr.colorOnSurfaceInverse)
      )
      putString(
        "onSurfaceVariant",
        getColorFromAttr(com.google.android.material.R.attr.colorOnSurfaceVariant)
      )
      putString("onTertiary", getColorFromAttr(com.google.android.material.R.attr.colorOnTertiary))
      putString(
        "onTertiaryContainer",
        getColorFromAttr(com.google.android.material.R.attr.colorOnTertiaryContainer)
      )
      putString(
        "onTertiaryFixed", getColorFromAttr(com.google.android.material.R.attr.colorOnTertiaryFixed)
      )
      putString(
        "onTertiaryFixedVariant",
        getColorFromAttr(com.google.android.material.R.attr.colorOnTertiaryFixedVariant)
      )
      putString("outline", getColorFromAttr(com.google.android.material.R.attr.colorOutline))
      putString(
        "outlineVariant", getColorFromAttr(com.google.android.material.R.attr.colorOutlineVariant)
      )
      putString(
        "primaryContainer",
        getColorFromAttr(com.google.android.material.R.attr.colorPrimaryContainer)
      )
      putString(
        "primaryFixed", getColorFromAttr(com.google.android.material.R.attr.colorPrimaryFixed)
      )
      putString(
        "primaryFixedDim", getColorFromAttr(com.google.android.material.R.attr.colorPrimaryFixedDim)
      )
      putString(
        "primaryInverse", getColorFromAttr(com.google.android.material.R.attr.colorPrimaryInverse)
      )
      putString(
        "primarySurface", getColorFromAttr(com.google.android.material.R.attr.colorPrimarySurface)
      )
      putString(
        "primaryVariant", getColorFromAttr(com.google.android.material.R.attr.colorPrimaryVariant)
      )
      putString("secondary", getColorFromAttr(com.google.android.material.R.attr.colorSecondary))
      putString(
        "secondaryContainer",
        getColorFromAttr(com.google.android.material.R.attr.colorSecondaryContainer)
      )
      putString(
        "secondaryFixed", getColorFromAttr(com.google.android.material.R.attr.colorSecondaryFixed)
      )
      putString(
        "secondaryFixedDim",
        getColorFromAttr(com.google.android.material.R.attr.colorSecondaryFixedDim)
      )
      putString(
        "secondaryVariant",
        getColorFromAttr(com.google.android.material.R.attr.colorSecondaryVariant)
      )
      putString("surface", getColorFromAttr(com.google.android.material.R.attr.colorSurface))
      putString(
        "surfaceBright", getColorFromAttr(com.google.android.material.R.attr.colorSurfaceBright)
      )
      putString(
        "surfaceContainer",
        getColorFromAttr(com.google.android.material.R.attr.colorSurfaceContainer)
      )
      putString(
        "surfaceContainerHigh",
        getColorFromAttr(com.google.android.material.R.attr.colorSurfaceContainerHigh)
      )
      putString(
        "surfaceContainerHighest",
        getColorFromAttr(com.google.android.material.R.attr.colorSurfaceContainerHighest)
      )
      putString(
        "surfaceContainerLow",
        getColorFromAttr(com.google.android.material.R.attr.colorSurfaceContainerLow)
      )
      putString(
        "surfaceContainerLowest",
        getColorFromAttr(com.google.android.material.R.attr.colorSurfaceContainerLowest)
      )
      putString("surfaceDim", getColorFromAttr(com.google.android.material.R.attr.colorSurfaceDim))
      putString(
        "surfaceInverse", getColorFromAttr(com.google.android.material.R.attr.colorSurfaceInverse)
      )
      putString(
        "surfaceVariant", getColorFromAttr(com.google.android.material.R.attr.colorSurfaceVariant)
      )
      putString("tertiary", getColorFromAttr(com.google.android.material.R.attr.colorTertiary))
      putString(
        "tertiaryContainer",
        getColorFromAttr(com.google.android.material.R.attr.colorTertiaryContainer)
      )
      putString(
        "tertiaryFixed", getColorFromAttr(com.google.android.material.R.attr.colorTertiaryFixed)
      )
      putString(
        "tertiaryFixedDim",
        getColorFromAttr(com.google.android.material.R.attr.colorTertiaryFixedDim)
      )
    }

    return colors
  }


  private fun getColorFromAttr(@AttrRes attr: Int): String {
    val typedArray = themedContext!!.theme.obtainStyledAttributes(intArrayOf(attr))
    val color = try {
      typedArray.getColor(0, 0)
    } finally {
      typedArray.recycle()
    }

    val alpha = (color shr 24) and 0xFF
    val red = (color shr 16) and 0xFF
    val green = (color shr 8) and 0xFF
    val blue = color and 0xFF

    return "#" + red.toString(16).padStart(2, '0').uppercase() + green.toString(16).padStart(2, '0')
      .uppercase() + blue.toString(16).padStart(2, '0').uppercase() + alpha.toString(16)
      .padStart(2, '0').uppercase()
  }

  private fun determineContext() {
    val newConfig = Configuration(currentActivity!!.resources.configuration).apply {
      uiMode = if (isDarkMode) {
        (uiMode and Configuration.UI_MODE_NIGHT_MASK.inv()) or Configuration.UI_MODE_NIGHT_YES
      } else {
        (uiMode and Configuration.UI_MODE_NIGHT_MASK.inv()) or Configuration.UI_MODE_NIGHT_NO
      }
    }

    val newContext = currentActivity!!.createConfigurationContext(newConfig)
    themedContext = ContextThemeWrapper(newContext, currentActivity!!.applicationInfo.theme)

    if (DYNAMIC_COLORS_ENABLED) {
      themedContext = DynamicColors.wrapContextIfAvailable(themedContext as ContextThemeWrapper)
    }
  }

  private fun switchToDarkMode() {
    isDarkMode = true
    determineContext()
  }
}
