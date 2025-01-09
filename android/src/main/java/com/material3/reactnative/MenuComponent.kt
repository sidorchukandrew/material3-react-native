package com.material3.reactnative

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.view.Gravity
import android.view.Menu
import android.view.SubMenu
import android.view.View
import androidx.appcompat.widget.PopupMenu
import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import kotlin.collections.HashMap

class MenuComponent(
  val props: ReadableMap,
  val reactContext: ReactApplicationContext,
  anchorView: View,
  val currentActivity: Context,
  val items: ReadableArray,
  val onSelect: Callback?,
) {
  private val popupMenu: PopupMenu = PopupMenu(anchorView.context, anchorView);
  private var alreadyCalled = false

  init {
    configureMenu()
    setItemsRecursively(items.toArrayList(), null, popupMenu.menu)
    setListener()
  }

  fun show() {
    popupMenu.show()
  }

  private fun configureMenu() {
    popupMenu.setForceShowIcon(true)
    popupMenu.gravity = getGravity()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) popupMenu.menu.setGroupDividerEnabled(true)
  }

  private fun setListener() {
    popupMenu.setOnMenuItemClickListener {
      if (it.hasSubMenu()) return@setOnMenuItemClickListener true
      if (alreadyCalled) return@setOnMenuItemClickListener true

      onSelect?.invoke(it.itemId)
      alreadyCalled = true
      true
    }
  }

  private fun setItemsRecursively(items: ArrayList<Any>?, subMenu: SubMenu?, menu: Menu?) {
    if (items == null || items.size == 0) return

    items.forEach {
      when (it) {
        is HashMap<*, *> -> processItemOrSubMenu(it as HashMap<String, Any>, subMenu, menu)
        else -> println("Unknown type")
      }
    }
  }

  private fun processItemOrSubMenu(item: HashMap<String, Any>, subMenu: SubMenu?, menu: Menu?) {
    val type = item["type"].toString()

    when (type) {
      "submenu" -> addSubMenu(item, subMenu, menu)
      "item" -> addItem(item, subMenu, menu)
    }
  }

  private fun addItem(item: HashMap<String, Any>, subMenu: SubMenu?, menu: Menu?) {
    val title = item["title"]?.toString()
    val groupId = getGroupId(item)
    val id = getItemId(item)

    val menuItem = if (menu != null) menu.add(groupId, id, Menu.NONE, title) else subMenu!!.add(
      groupId, id, Menu.NONE, title
    )


    menuItem.isCheckable = getIsCheckable(item)
    menuItem.isChecked = getIsChecked(item)
    menuItem.icon = IconHelper(currentActivity, item["icon"].toString()).resolve()
  }

  private fun addSubMenu(item: HashMap<String, Any>, subMenu: SubMenu?, menu: Menu?) {
    val title = item["title"]?.toString()
    val groupId = getGroupId(item)

    val newSubMenu = if (menu != null) menu.addSubMenu(
      groupId, Menu.NONE, Menu.NONE, title
    ) else subMenu!!.addSubMenu(
      groupId, Menu.NONE, Menu.NONE, title
    )

    newSubMenu.setIcon(IconHelper(currentActivity, item["icon"].toString()).resolve())

    if (!item.containsKey("items")) return

    val subMenuItems = item["items"]
    if (subMenuItems is ArrayList<*>) setItemsRecursively(
      subMenuItems as ArrayList<Any>, newSubMenu, null
    )
  }

  private fun getGroupId(item: HashMap<String, Any>): Int {
    if (!item.containsKey("groupId")) return Menu.NONE

    val groupId = item["groupId"]
    if (groupId is Double) return groupId.toInt()
    if (groupId is Int) return groupId

    return Menu.NONE
  }

  private fun getItemId(item: HashMap<String, Any>): Int {
    if (!item.containsKey("itemId")) return Menu.NONE

    val itemId = item["itemId"]
    if (itemId is Double) return itemId.toInt()
    if (itemId is Int) return itemId

    return Menu.NONE
  }

  private fun getIsChecked(item: HashMap<String, Any>): Boolean {
    if (!item.containsKey("isChecked")) return false

    val isChecked = item["isChecked"]
    if (isChecked is Boolean) return isChecked

    return false
  }

  private fun getIsCheckable(item: HashMap<String, Any>): Boolean {
    if (!item.containsKey("isCheckable")) return false

    val isCheckable = item["isCheckable"]
    if (isCheckable is Boolean) return isCheckable

    return false
  }

  private fun getGravity(): Int {
    val gravity = props.getString("gravity")
    return when (gravity) {
      "top" -> Gravity.TOP
      "bottom" -> Gravity.BOTTOM
      "left" -> Gravity.LEFT
      "right" -> Gravity.RIGHT
      "center" -> Gravity.CENTER
      "center_vertical" -> Gravity.CENTER_VERTICAL
      "center_horizontal" -> Gravity.CENTER_HORIZONTAL
      "fill" -> Gravity.FILL
      "fill_vertical" -> Gravity.FILL_VERTICAL
      "fill_horizontal" -> Gravity.FILL_HORIZONTAL
      "start" -> Gravity.START
      "end" -> Gravity.END
      "clip_vertical" -> Gravity.CLIP_VERTICAL
      "clip_horizontal" -> Gravity.CLIP_HORIZONTAL
      "no_gravity" -> Gravity.NO_GRAVITY
      else -> Gravity.NO_GRAVITY
    }
  }
}
