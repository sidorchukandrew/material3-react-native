package com.material3.reactnative

import com.facebook.react.BaseReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.model.ReactModuleInfo
import com.facebook.react.module.model.ReactModuleInfoProvider
import com.facebook.react.uimanager.ViewManager
import java.util.ArrayList

class Material3Package : BaseReactPackage() {
  override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> {
    val viewManagers: MutableList<ViewManager<*, *>> = ArrayList()
//    viewManagers.add(DividerManager(reactContext))

    return viewManagers
  }

  override fun getModule(name: String, reactContext: ReactApplicationContext): NativeModule? {
    return when (name) {
      DatePickerModule.NAME -> DatePickerModule(reactContext)
      TimePickerModule.NAME -> TimePickerModule(reactContext)
      SnackbarModule.NAME -> SnackbarModule(reactContext)
      AlertDialogModule.NAME -> AlertDialogModule(reactContext)
      OptionsDialogModule.NAME -> OptionsDialogModule(reactContext)
      RangePickerModule.NAME -> RangePickerModule(reactContext)
      MenuModule.NAME -> MenuModule(reactContext)
      ColorsModule.NAME -> ColorsModule(reactContext)
      else -> null
    }
  }

  override fun getReactModuleInfoProvider() = ReactModuleInfoProvider {
    mapOf(
      DatePickerModule.NAME to ReactModuleInfo(
        DatePickerModule.NAME, DatePickerModule.NAME, false, // canOverrideExistingModule
        false, // needsEagerInit
        false, // isCxxModule
        true // isTurboModule
      ), TimePickerModule.NAME to ReactModuleInfo(
        TimePickerModule.NAME, TimePickerModule.NAME, false, // canOverrideExistingModule
        false, // needsEagerInit
        false, // isCxxModule
        true // isTurboModule
      ), SnackbarModule.NAME to ReactModuleInfo(
        SnackbarModule.NAME, SnackbarModule.NAME, false, // canOverrideExistingModule
        false, // needsEagerInit
        false, // isCxxModule
        true // isTurboModule
      ), AlertDialogModule.NAME to ReactModuleInfo(
        AlertDialogModule.NAME, AlertDialogModule.NAME, false, // canOverrideExistingModule
        false, // needsEagerInit
        false, // isCxxModule
        true // isTurboModule
      ), OptionsDialogModule.NAME to ReactModuleInfo(
        OptionsDialogModule.NAME, OptionsDialogModule.NAME, false, // canOverrideExistingModule
        false, // needsEagerInit
        false, // isCxxModule
        true // isTurboModule
      ), RangePickerModule.NAME to ReactModuleInfo(
        RangePickerModule.NAME, RangePickerModule.NAME, false, // canOverrideExistingModule
        false, // needsEagerInit
        false, // isCxxModule
        true // isTurboModule
      ), MenuModule.NAME to ReactModuleInfo(
        MenuModule.NAME, MenuModule.NAME, false, // canOverrideExistingModule
        false, // needsEagerInit
        false, // isCxxModule
        true // isTurboModule
      ), ColorsModule.NAME to ReactModuleInfo(
        ColorsModule.NAME, ColorsModule.NAME, false, // canOverrideExistingModule
        false, // needsEagerInit
        false, // isCxxModule
        true // isTurboModule
      )
    )
  }
}
