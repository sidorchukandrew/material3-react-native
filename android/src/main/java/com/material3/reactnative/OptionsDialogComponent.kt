package com.material3.reactnative

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableNativeArray
import com.facebook.react.bridge.WritableNativeMap
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class OptionsDialogComponent(
  val props: ReadableMap,
  val onPositivePress: Callback?,
  val onNegativePress: Callback?,
  val onNeutralPress: Callback?,
  val onCancel: Callback?,
  val reactContext: ReactApplicationContext,
) : DialogFragment() {
  private var alreadyCalled = false
  private var alertDialog: MaterialAlertDialogBuilder? = null
  private var pendingSelections: Array<Int> = arrayOf()

  fun show() {
    val activity = reactContext.currentActivity as FragmentActivity?
    val fragmentManager = activity!!.supportFragmentManager

    this.show(fragmentManager, AlertDialogModule.NAME)
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    buildDialog()
    return alertDialog!!.create()
  }

  private fun buildDialog() {
    alertDialog = MaterialAlertDialogBuilder(
      requireContext(), getHeaderAlignmentTheme()
    )

    setTitle()
    setOptions()
    setActions()
    setCancelable()
    setIcon()
  }

  private fun setActions() {
    val positiveButtonText = props.getString("positiveButtonText")
    if (!positiveButtonText.isNullOrEmpty()) {
      alertDialog!!.setPositiveButton(positiveButtonText) { _, _ ->
        if (alreadyCalled) return@setPositiveButton

        onPositivePress?.invoke(getResult())
        alreadyCalled = true
      }
    }

    val negativeButtonText = props.getString("negativeButtonText")
    if (!negativeButtonText.isNullOrEmpty()) {
      alertDialog!!.setNegativeButton(negativeButtonText) { _, _ ->
        if (alreadyCalled) return@setNegativeButton

        onNegativePress?.invoke(getResult())
        alreadyCalled = true
      }
    }

    val neutralButtonText = props.getString("neutralButtonText")
    if (!neutralButtonText.isNullOrEmpty()) {
      alertDialog!!.setNeutralButton(neutralButtonText) { _, _ ->
        if (alreadyCalled) return@setNeutralButton

        onNeutralPress?.invoke(getResult())
        alreadyCalled = true
      }
    }
  }

  private fun setTitle() {
    val title = props.getString("title")
    if (title.isNullOrEmpty()) return

    alertDialog!!.setTitle(title)
  }

  private fun setOptions() {
    if (!props.hasKey("options")) return

    val options = props.getArray("options")
    if (options == null || options.size() == 0) return

    val pickerType = props.getString("pickerType")
    val convertedOptions = options.toArrayList().map { it.toString() }.toTypedArray()

    when (pickerType) {
      "singlechoice" -> configureSingleChoice(convertedOptions)
      "multiselect" -> configureMultiSelect(convertedOptions)
      else -> configureRows(convertedOptions)
    }
  }

  private fun setCancelable() {
    if (props.hasKey("cancelable")) {
      this.isCancelable = props.getBoolean("cancelable")
    }
  }

  private fun getHeaderAlignmentTheme(): Int {
    val headerAlignment = props.getString("headerAlignment")

    return when (headerAlignment) {
      "center" -> com.google.android.material.R.style.ThemeOverlay_Material3_MaterialAlertDialog_Centered
      else -> com.google.android.material.R.style.ThemeOverlay_Material3_MaterialAlertDialog
    }
  }

  override fun onDismiss(dialog: DialogInterface) {
    if (alreadyCalled) return

    onCancel?.invoke(getResult())
    alreadyCalled = true
  }

  private fun configureRows(options: Array<String>) {
    alertDialog!!.setItems(options) { _, selectedIndex: Int ->
      if (alreadyCalled) return@setItems

      pendingSelections = arrayOf(selectedIndex)
      val result = getResult()
      onPositivePress?.invoke(result)
      alreadyCalled = true
    }
  }

  private fun configureSingleChoice(options: Array<String>) {
    val selected = props.getArray("selected")
    val selectedIndex = if (selected == null || selected.size() == 0) -1 else selected.getInt(0)

    alertDialog!!.setSingleChoiceItems(options, selectedIndex) { _, newIndex: Int ->
      pendingSelections = arrayOf(newIndex)
    }
  }

  private fun configureMultiSelect(options: Array<String>) {
    val selected = props.getArray("selected") ?: WritableNativeArray()
    val checkedItems = BooleanArray(options.size) { false }

    selected.toArrayList().forEach { selectedIndex ->
      val parsedIndex = (selectedIndex as Double).toInt()
      if (parsedIndex < 0 || parsedIndex >= options.size) return@forEach

      checkedItems[parsedIndex] = true
    }

    alertDialog!!.setMultiChoiceItems(
      options, checkedItems
    ) { _, selectedIndex: Int, isChecked: Boolean ->
      pendingSelections = if (isChecked) {
        pendingSelections.plus(selectedIndex).distinct().sorted().toTypedArray()
      } else {
        pendingSelections.filter { it != selectedIndex }.distinct().sorted().toTypedArray()
      }
    }
  }

  private fun getResult(): WritableNativeMap {
    val result = WritableNativeMap()
    val selections = WritableNativeArray()

    pendingSelections.forEach { selections.pushInt(it) }
    result.putArray("selections", selections)

    return result
  }

  private fun setIcon() {
    val icon = props.getString("icon")

    if (icon.isNullOrEmpty()) {
      alertDialog!!.setIcon(null)
    } else {
      alertDialog!!.setIcon(IconHelper(alertDialog!!.context, icon).resolve())
    }
  }
}
