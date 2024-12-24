package com.material3.reactnative

import androidx.core.util.Pair
import androidx.fragment.app.FragmentActivity
import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableMap
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.CompositeDateValidator
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker

class RangePickerComponent(
  val props: ReadableMap,
  val onChange: Callback?,
  val onCancel: Callback?,
  val reactContext: ReactApplicationContext,
  val promise: Promise
) {
  private val builder: MaterialDatePicker.Builder<Pair<Long, Long>> =
    MaterialDatePicker.Builder.dateRangePicker()
  private val rangePicker: MaterialDatePicker<Pair<Long, Long>>
  private var alreadyCalled = false

  init {
    setTitle()
    setInputMode()
    setFullscreen()
    setButtonTexts()
    setValue()
    setConstraints()
    rangePicker = builder.build()

    setCallbacks()
  }

  fun show() {
    val fragmentManager = (reactContext.currentActivity as FragmentActivity).supportFragmentManager
    rangePicker.show(fragmentManager, DatePickerModule.NAME)
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
      "text" -> builder.setInputMode(MaterialDatePicker.INPUT_MODE_TEXT)
      "calendar" -> builder.setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
      else -> builder.setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
    }
  }

  private fun setFullscreen() {
    if (!props.hasKey("fullscreen")) return

    val fullscreen = props.getBoolean("fullscreen")
    if (fullscreen) {
      builder.setTheme(com.google.android.material.R.style.ThemeOverlay_Material3_MaterialCalendar_Fullscreen)
    } else {
      builder.setTheme(com.google.android.material.R.style.ThemeOverlay_Material3_MaterialCalendar)
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

  private fun setValue() {
    var start: Long? = null
    var end: Long? = null

    if (props.hasKey("start")) {
      start = props.getDouble("start").toLong()
    }

    if (props.hasKey("end")) {
      end = props.getDouble("end").toLong()
    }

    builder.setSelection(Pair(start, end))
  }

  private fun setConstraints() {
    val constraintsBuilder = CalendarConstraints.Builder()

    if (props.hasKey("firstDayOfWeek")) {
      constraintsBuilder.setFirstDayOfWeek(props.getInt("firstDayOfWeek"))
    }

    val validators = mutableListOf<CalendarConstraints.DateValidator>()

    if (props.hasKey("minDate")) {
      val minDate = props.getDouble("minDate").toLong()
      validators.add(DateValidatorPointForward.from(minDate))
    }

    if (props.hasKey("maxDate")) {
      val maxDate = props.getDouble("maxDate").toLong()
      validators.add(DateValidatorPointBackward.before(maxDate))
    }

    constraintsBuilder.setValidator(CompositeDateValidator.allOf(validators))
    builder.setCalendarConstraints(constraintsBuilder.build())
  }

  private fun setCallbacks() {
    rangePicker.addOnPositiveButtonClickListener {
      if (alreadyCalled) return@addOnPositiveButtonClickListener

      alreadyCalled = true
      onChange?.invoke(it.first.toDouble(), it.second.toDouble())
      promise.resolve(null)
    }

    rangePicker.addOnCancelListener { triggerCancel() }
    rangePicker.addOnDismissListener { triggerCancel() }
    rangePicker.addOnNegativeButtonClickListener { triggerCancel() }
  }


  private fun triggerCancel() {
    if (alreadyCalled) return

    alreadyCalled = true
    onCancel?.invoke()
    promise.resolve(null)
  }
}
