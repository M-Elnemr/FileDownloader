package com.nagwa.filedownloader.base.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.nagwa.filedownloader.BuildConfig

abstract class BaseViewModel(
    private val application: Application
) : ViewModel() {

    fun getVersion(): String {
        var versionName = ""

        try {
            versionName = application
                .packageManager
                .getPackageInfo(application.packageName, 0).versionName

            val versionParts = versionName.split('-')

            if (!BuildConfig.BUILD_TYPE.contains("release")) {
                versionName = String.format("%s (%s)", versionParts[0], versionParts[1])
            }

            for (part in versionParts) {
                if (part == "DEBUG") {
                    versionName += "-DEBUG"
                    break
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return versionName
    }
}
