package com.nagwa.filedownloader.base.extension

import android.app.Activity
import android.view.View

fun Activity.getContentView(): View = this.findViewById(android.R.id.content)
