package com.material3.reactnative

import android.view.View
import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap

class MenuModule(reactContext: ReactApplicationContext) : NativeMenuSpec(reactContext) {
  override fun getName() = NAME

  override fun show(
    props: ReadableMap?, items: ReadableArray?, onSelect: Callback?
  ) {
    if (props == null || items == null) return

    val id = props.getInt("id")
    val view = currentActivity?.findViewById<View>(id) ?: return

    val menu = MenuComponent(
      props = props,
      reactContext = reactApplicationContext,
      items = items,
      anchorView = view,
      currentActivity = currentActivity!!,
      onSelect = onSelect
    )

    menu.show()
  }

  companion object {
    const val NAME = "RTNMenu"
  }
}
