package com.nagwa.filedownloader.ui.main.view

import android.Manifest
import android.annotation.TargetApi
import android.app.DownloadManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.*
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.nagwa.filedownloader.R
import com.nagwa.filedownloader.base.extension.observe
import com.nagwa.filedownloader.base.network.model.FileResponseDto
import com.nagwa.filedownloader.base.view.BaseActivity
import com.nagwa.filedownloader.ui.main.adapter.movies.MoviesAdapter
import com.nagwa.filedownloader.ui.main.adapter.movies.MoviesAdapterActionsInterface
import com.nagwa.filedownloader.ui.main.viewmodel.MainViewModel
import com.nagwa.filedownloader.ui.main.viewmodel.MainViewModelFactory
import com.nagwa.filedownloader.utils.Constants
import com.nagwa.filedownloader.utils.crash.CrashReportActivity
import com.nagwa.filedownloader.utils.crash.HandleAppCrash
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity(override val layoutResourceId: Int = R.layout.activity_main) : BaseActivity(),
    MoviesAdapterActionsInterface {
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    lateinit var preferences: SharedPreferences


    private val downloadManager: DownloadManager by lazy { getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager }
    private val references = mutableListOf<Long>()
    private val notificationID = 101
    private val channelID = "com.nagwa.filedownloader"

    private val notificationManager: NotificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    val onComplete = object : BroadcastReceiver() {

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onReceive(context: Context, intent: Intent) {

            val referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)

            references.remove(referenceId)

            if (references.isEmpty()) {
                sendNotification()
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        HandleAppCrash.deploy(this, CrashReportActivity::class.java)

        preferences = getSharedPreferences(
            Constants.APP_NAME,
            Context.MODE_PRIVATE
        )
        createNotificationChannelAndRegisterReceiver()

        initRecyclerView()

        grantPermissions()

        viewModel.fetchFiles()
    }

    private fun grantPermissions(data: FileResponseDto? = null) {
        Dexter.withContext(this).withPermissions(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                data?.let { download(data) }
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<PermissionRequest>?,
                p1: PermissionToken?
            ) {
                Toast.makeText(this@MainActivity, "Permission not granted", Toast.LENGTH_SHORT).show()
                p1?.continuePermissionRequest()
            }

        }).check()
    }

    private fun download(data: FileResponseDto) {
        try {

            val extension = when(data.type){
                "PDF" -> "pdf"
                "VIDEO" -> "mp4"
                else -> "jpg"
            }

            val request = DownloadManager.Request(Uri.parse(data.url?: ""))
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setTitle(data.name)
                .setDescription("Downloading")
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_MOVIES,
                    "/Nagwa//${data.name}.$extension"
                )

            references.add(downloadManager.enqueue(request))

        } catch (e: java.lang.Exception) {
            Log.d("TAG", "downloadException: $e")
            Toast.makeText(this, "download failed", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun initRecyclerView() {
        rv_movies.layoutManager = LinearLayoutManager(this)
        rv_movies.adapter = moviesAdapter
        moviesAdapter.setInterface(this)
    }

    override fun setUpViewModelStateObservers() {
        observe(viewModel.getState()) { onStateChanged(it) }
    }

    private fun onStateChanged(state: MainViewModel.State) = when (state) {
        is MainViewModel.State.ShowError -> {
        }
        MainViewModel.State.ShowLoading -> {
            //TODO show loading indicator
        }
        is MainViewModel.State.FileFetched -> showFiles(state.files)
    }

    private fun showFiles(files: List<FileResponseDto>) {
        moviesAdapter.setMovies(files)
    }

    override fun onDownloadClicked(data: FileResponseDto) {
        grantPermissions(data)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendNotification() {

        val notification = Notification.Builder(this, channelID)
            .setContentTitle("Download Manager")
            .setContentText("All download complete")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setChannelId(channelID)
            .setNumber(10)
            .build()

        notificationManager.notify(notificationID, notification)
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {

        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(
            channelID,
            "DownloadManager",
            importance
        )
        channel.description = "All download complete"
        channel.enableLights(true)
        channel.lightColor = Color.RED
        channel.enableVibration(true)
        notificationManager.createNotificationChannel(channel)
    }

    private fun createNotificationChannelAndRegisterReceiver() {
        createNotificationChannel()

        registerReceiver(
            onComplete,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        )
    }

    private fun unRegisterBroadcast() {
        unregisterReceiver(onComplete)
    }

    override fun onDestroy() {
        super.onDestroy()
        unRegisterBroadcast()
    }

}
