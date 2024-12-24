package com.material3.reactnative

import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.UiThreadUtil

class RangePickerModule(reactContext: ReactApplicationContext) : NativeDatePickerSpec(reactContext)  {
  override fun getName() = NAME

  override fun show(props: ReadableMap?, onChange: Callback?, onCancel: Callback?, promise: Promise) {
    val datePicker = RangePickerComponent(
      props = props!!,
      onChange = onChange,
      onCancel = onCancel,
      reactContext = reactApplicationContext,
      promise = promise
    )

    UiThreadUtil.runOnUiThread {
      datePicker.show()
    }
  }

  companion object {
    const val NAME = "RTNRangePicker"
  }
}
