package com.material3.reactnative

import androidx.fragment.app.FragmentActivity
import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableMap
import com.google.android.material.timepicker.MaterialTimePicker
import android.text.format.DateFormat;
import com.facebook.react.bridge.WritableNativeMap
import com.google.android.material.timepicker.TimeFormat

class TimePickerComponent(
  val props: ReadableMap,
  val onChange: Callback?,
  val onCancel: Callback?,
  val reactContext: ReactApplicationContext,
  val promise: Promise
) {
  private val builder: MaterialTimePicker.Builder = MaterialTimePicker.Builder()
  private val timePicker: MaterialTimePicker
  private var alreadyCalled = false

  init {
    setTitle()
    setInputMode()
    setButtonTexts()
    setValue()
    set24HourMode()
    timePicker = builder.build()

    setCallbacks()
  }

  fun show() {
    val fragmentManager = (reactContext.currentActivity as FragmentActivity).supportFragmentManager
    timePicker.show(fragmentManager, TimePickerModule.NAME)
  }


  private fun setTitle() {
    val title = props.getString("title")
    if (title.isNullOrEmpty()) return

    builder.setTitleText(title)
  }

  private fun setInputMode() {
    val inputMode = props.getString("inputMode")
    if (inputMode.isNullOrEmpty()) return

    when (inputMode) {
      "keyboard" -> builder.setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
      else -> builder.setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
    }
  }

  private fun setButtonTexts() {
    val positiveButtonText = props.getString("positiveButtonText")

    if (!positiveButtonText.isNullOrEmpty()) {
      builder.setPositiveButtonText(positiveButtonText)
    }

    val negativeButtonText = props.getString("negativeButtonText")

    if (!negativeButtonText.isNullOrEmpty()) {
      builder.setNegativeButtonText(negativeButtonText)
    }
  }

  private fun set24HourMode() {
    var is24HourFormat = DateFormat.is24HourFormat(reactContext)

    if (props.hasKey("is24HourFormat")) {
      is24HourFormat = props.getBoolean("is24HourFormat")
    }

    if (is24HourFormat) {
      builder.setTimeFormat(TimeFormat.CLOCK_24H)
    } else {
      builder.setTimeFormat(TimeFormat.CLOCK_12H)
    }
  }

  private fun setValue() {

    if (props.hasKey("hour")) {
      builder.setHour(props.getInt("hour"))
    }


    if (props.hasKey("minute")) {
      builder.setMinute(props.getInt("minute"))
    }
  }


  private fun setCallbacks() {
    timePicker.addOnPositiveButtonClickListener {
      if (alreadyCalled) return@addOnPositiveButtonClickListener

      val result = WritableNativeMap()
      result.putInt("hour", timePicker.hour)
      result.putInt("minute", timePicker.minute)
      onChange?.invoke(result)

      promise.resolve(null)
      alreadyCalled = true
    }

    timePicker.addOnCancelListener {
      triggerCancel()

    }

    timePicker.addOnDismissListener {
      triggerCancel()
    }

    timePicker.addOnNegativeButtonClickListener {
      triggerCancel()
    }
  }


  private fun triggerCancel() {
    if (alreadyCalled) return

    alreadyCalled = true
    onCancel?.invoke()
    promise.resolve(null)
  }
}
