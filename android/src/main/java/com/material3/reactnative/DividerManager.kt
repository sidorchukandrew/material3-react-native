package com.material3.reactnative

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewManagerDelegate
import com.facebook.react.viewmanagers.RTNDividerManagerInterface
import com.facebook.react.viewmanagers.RTNDividerManagerDelegate

@ReactModule(name = DividerManager.NAME)
class DividerManager(context: ReactApplicationContext) : SimpleViewManager<DividerComponent>(), RTNDividerManagerInterface<DividerComponent> {
  private val delegate: RTNDividerManagerDelegate<DividerComponent, DividerManager> = RTNDividerManagerDelegate(this)

  override fun getDelegate(): ViewManagerDelegate<DividerComponent> = delegate

  override fun getName(): String = NAME

  override fun createViewInstance(context: ThemedReactContext): DividerComponent = DividerComponent(context)

  companion object {
    const val NAME = "RTNDivider"
  }

  override fun setColor(view: DividerComponent?, value: String?) {
//    TODO("Not yet implemented")
  }
}
