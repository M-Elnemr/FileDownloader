package com.nagwa.filedownloader.base.extension

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.setMargin(left: Int?, top: Int?, right: Int?, bottom: Int?) {
    val lp = layoutParams as? ViewGroup.MarginLayoutParams ?: return

    lp.setMargins(
        left ?: lp.leftMargin,
        top ?: lp.topMargin,
        right ?: lp.rightMargin,
        bottom ?: lp.rightMargin
    )

    layoutParams = lp
}