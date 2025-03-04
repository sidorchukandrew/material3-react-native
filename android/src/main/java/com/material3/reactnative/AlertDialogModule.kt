package com.material3.reactnative

import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.UiThreadUtil

class AlertDialogModule(reactContext: ReactApplicationContext) :
  NativeAlertDialogSpec(reactContext) {
  override fun getName() = NAME

  override fun show(
    props: ReadableMap?,
    onPositivePress: Callback?,
    onNegativePress: Callback?,
    onNeutralPress: Callback?,
    onCancel: Callback?
  ) {
    val alertDialog = AlertDialogComponent(
      props = props!!,
      onPositivePress = onPositivePress,
      onNeutralPress = onNeutralPress,
      reactContext = reactApplicationContext,
      onNegativePress = onNegativePress,
      onCancel = onCancel
    )

    UiThreadUtil.runOnUiThread {
      alertDialog.show()
    }
  }

  companion object {
    const val NAME = "RTNAlertDialog"
  }
}
