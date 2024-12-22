package com.material3.reactnative

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.facebook.react.bridge.ReactApplicationContext
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.button.MaterialButton
import com.google.android.material.divider.MaterialDivider
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.search.SearchBar
import com.google.android.material.search.SearchView
import com.google.android.material.slider.Slider

class DividerComponent : BottomAppBar {
  constructor(context: Context) : super(context) {
    configureComponent()
  }

  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
    configureComponent()
  }

  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
    context, attrs, defStyleAttr
  ) {
    configureComponent()
  }

  private fun configureComponent() {

    this.layoutParams = CoordinatorLayout.LayoutParams(
      CoordinatorLayout.LayoutParams.MATCH_PARENT,
      CoordinatorLayout.LayoutParams.WRAP_CONTENT,
    )
  }
}
