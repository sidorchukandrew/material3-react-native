package com.material3.reactnative

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableMap
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AlertDialogComponent(
  val props: ReadableMap,
  val onPositivePress: Callback?,
  val onNegativePress: Callback?,
  val onNeutralPress: Callback?,
  val onCancel: Callback?,
  val reactContext: ReactApplicationContext,
) : DialogFragment() {
  private var alreadyCalled = false
  private var alertDialog: MaterialAlertDialogBuilder? = null

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
    setMessage()
    setActions()
    setCancelable()
    setIcon()
  }

  private fun setActions() {
    val positiveButtonText = props.getString("positiveButtonText")
    if (!positiveButtonText.isNullOrEmpty()) {
      alertDialog!!.setPositiveButton(positiveButtonText) { _, _ ->
        if (alreadyCalled) return@setPositiveButton

        onPositivePress?.invoke()
        alreadyCalled = true
      }
    }

    val negativeButtonText = props.getString("negativeButtonText")
    if (!negativeButtonText.isNullOrEmpty()) {
      alertDialog!!.setNegativeButton(negativeButtonText) { _, _ ->
        if (alreadyCalled) return@setNegativeButton

        onNegativePress?.invoke()
        alreadyCalled = true
      }
    }

    val neutralButtonText = props.getString("neutralButtonText")
    if (!neutralButtonText.isNullOrEmpty()) {
      alertDialog!!.setNeutralButton(neutralButtonText) { _, _ ->
        if (alreadyCalled) return@setNeutralButton

        onNeutralPress?.invoke()
        alreadyCalled = true
      }
    }
  }

  private fun setTitle() {
    val title = props.getString("title")
    if (title.isNullOrEmpty()) return

    alertDialog!!.setTitle(title)
  }

  private fun setMessage() {
    val message = props.getString("message")
    if (message.isNullOrEmpty()) return

    alertDialog!!.setMessage(message)
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

  private fun setIcon() {
    val icon = props.getString("icon")

    if (icon.isNullOrEmpty()) {
      alertDialog!!.setIcon(null)
    } else {
      alertDialog!!.setIcon(IconHelper(alertDialog!!.context, icon).resolve())
    }
  }

  override fun onDismiss(dialog: DialogInterface) {
    if (alreadyCalled) return

    onCancel?.invoke()
    alreadyCalled = true
  }
}
