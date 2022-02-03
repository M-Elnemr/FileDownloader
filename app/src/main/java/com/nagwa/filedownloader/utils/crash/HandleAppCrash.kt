package com.nagwa.filedownloader.utils.crash

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageInfo
import android.os.Build
import android.os.Environment
import android.os.Process
import android.os.StatFs
import android.text.format.DateFormat
import android.util.Log
import java.io.PrintWriter
import java.io.StringWriter
import java.util.*

class HandleAppCrash(private val context: Activity, var intentClass: Class<*>?) :
    Thread.UncaughtExceptionHandler {
    private val statFs: StatFs
        get() {
            val path = Environment.getDataDirectory()
            return StatFs(path.path)
        }

    private fun getAvailableInternalMemorySize(stat: StatFs): Long {
        val blockSize = stat.blockSize.toLong()
        val availableBlocks = stat.availableBlocks.toLong()
        return availableBlocks * blockSize
    }

    private fun getTotalInternalMemorySize(stat: StatFs): Long {
        val blockSize = stat.blockSize.toLong()
        val totalBlocks = stat.blockCount.toLong()
        return totalBlocks * blockSize
    }

    private fun addInformation(message: StringBuilder) {
        message.append("Locale: ").append(Locale.getDefault()).append('\n')
        try {
            val pm = context.packageManager
            val pi: PackageInfo
            pi = pm.getPackageInfo(context.packageName, 0)
            message.append("Version: ").append(pi.versionName).append('\n')
            message.append("Package: ").append(pi.packageName).append('\n')
        } catch (e: Exception) {
            Log.e("CustomExceptionHandler", "Error", e)
            message.append("Could not get Version information for ").append(context.packageName)
        }
        message.append("Phone Model: ").append(Build.MODEL).append('\n')
        message.append("Android Version: ").append(Build.VERSION.RELEASE).append('\n')
        message.append("Board: ").append(Build.BOARD).append('\n')
        message.append("Brand: ").append(Build.BRAND).append('\n')
        message.append("Device: ").append(Build.DEVICE).append('\n')
        message.append("Host: ").append(Build.HOST).append('\n')
        message.append("ID: ").append(Build.ID).append('\n')
        message.append("Model: ").append(Build.MODEL).append('\n')
        message.append("Product: ").append(Build.PRODUCT).append('\n')
        message.append("Type: ").append(Build.TYPE).append('\n')
        val stat = statFs
        message.append("Total Internal memory: ").append(getTotalInternalMemorySize(stat))
            .append('\n')
        message.append("Available Internal memory: ").append(getAvailableInternalMemorySize(stat))
            .append('\n')
    }

    override fun uncaughtException(thread: Thread, exception: Throwable) {
        try {
            val report = StringBuilder()
            val date = DateFormat.format("yyyy-MM-dd hh:mm:ss a", Date()).toString()
            report.append("Error Report collected on : ").append(date).append('\n').append('\n')
            report.append("Informations :").append('\n')
            addInformation(report)
            report.append('\n').append('\n')
            report.append("Stack:\n")
            val stackTrace = StringWriter()
            exception.printStackTrace(PrintWriter(stackTrace))
            System.err.println(stackTrace)
            report.append(stackTrace.toString())
            report.append('\n')
            report.append("**** End of current Report ***")
            //   sendError(report);
            val intent = Intent(context, intentClass)
            intent.putExtra("stackTrace", report.toString())
            context.startActivity(intent)
            Process.killProcess(Process.myPid())
            context.finish()
            System.exit(10)
            Log.e(HandleAppCrash::class.java.name, "Error while sendError$report")
        } catch (ignore: Throwable) {
            Log.e(HandleAppCrash::class.java.name, "Error while sending error ", ignore)
        }
    }

    companion object {
        fun deploy(context: Activity, intentClass: Class<*>?) {
            Thread.setDefaultUncaughtExceptionHandler(HandleAppCrash(context, intentClass))
        }
    }
}