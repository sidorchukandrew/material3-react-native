package com.material3.reactnative

import android.graphics.Color
import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableMap
import com.google.android.material.snackbar.Snackbar

class SnackbarComponent(
  val props: ReadableMap,
  private val onActionPress: Callback?,
  val reactContext: ReactApplicationContext
) {
  private val snackbar: Snackbar = Snackbar.make(
    reactContext.currentActivity!!.findViewById(android.R.id.content),
    "",
    Snackbar.LENGTH_SHORT
  )
  private var alreadyCalled = false

  init {
    setDuration()
    setText()
    setAnimationMode()
    setAction()
    setBackgroundColor()
  }

  fun show() {
    snackbar.show()
  }

  private fun setDuration() {
    val duration = props.getString("duration")

    when (duration) {
      "long" -> snackbar.duration = Snackbar.LENGTH_LONG
      "indefinite" -> snackbar.duration = Snackbar.LENGTH_INDEFINITE
      else -> snackbar.duration = Snackbar.LENGTH_SHORT
    }
  }

  private fun setText() {
    val text = props.getString("text")

    if (text.isNullOrEmpty()) return
    snackbar.setText(text)

    if (props.hasKey("textMaxLines")) {
      snackbar.setTextMaxLines(props.getInt("textMaxLines"))
    }

    val textColor = props.getString("textColor")
    if (!textColor.isNullOrEmpty()) {
      snackbar.setTextColor(android.graphics.Color.parseColor(textColor))
    }
  }

  private fun setAnimationMode() {
    val animationMode = props.getString("animationMode")

    when (animationMode) {
      "slide" -> snackbar.animationMode = Snackbar.ANIMATION_MODE_SLIDE
      else -> snackbar.animationMode = Snackbar.ANIMATION_MODE_FADE
    }
  }

  private fun setAction() {
    val actionText = props.getString("actionText")

    if (!actionText.isNullOrEmpty()) {
      snackbar.setAction(actionText) {
        if (alreadyCalled) return@setAction

        onActionPress?.invoke()
        alreadyCalled = true
      }
    }

    val actionTextColor = props.getString("actionTextColor")
    if (!actionTextColor.isNullOrEmpty()) {
      snackbar.setActionTextColor(android.graphics.Color.parseColor(actionTextColor))
    }
  }

  private fun setBackgroundColor() {
    val backgroundColor = props.getString("backgroundColor")
    if (backgroundColor.isNullOrEmpty()) return

    snackbar.setBackgroundTint(Color.parseColor(backgroundColor))
  }
}
