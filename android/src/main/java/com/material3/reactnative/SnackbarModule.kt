package com.material3.reactnative

import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.UiThreadUtil

class SnackbarModule(reactContext: ReactApplicationContext) : NativeSnackbarSpec(reactContext)  {
  override fun getName() = NAME

  override fun show(props: ReadableMap?, onActionPress: Callback?) {
    val snackbar = SnackbarComponent(
      props = props!!,
      onActionPress = onActionPress,
      reactContext = reactApplicationContext,
    )

    UiThreadUtil.runOnUiThread {
      snackbar.show()
    }
  }

  companion object {
    const val NAME = "RTNSnackbar"
  }
}
