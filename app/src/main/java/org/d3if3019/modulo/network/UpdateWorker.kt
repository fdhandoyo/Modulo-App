package org.d3if3019.modulo.network

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.d3if3019.modulo.MainActivity
import org.d3if3019.modulo.R

class UpdateWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    companion object {
        const val WORK_NAME = "updater"
        private const val NOTIFICATION_ID = 44
    }
    override suspend fun doWork(): Result {
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.e("Worker", "Tidak diberikan izin notifikasi")
            return Result.failure()
        }

        val builder = NotificationCompat.Builder(applicationContext,
            MainActivity.CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(applicationContext.getString(
                R.string.notif_title))
            .setContentText(applicationContext.getString(
                R.string.notif_text))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(getPendingIntent(applicationContext))
            .setAutoCancel(true)
        val manager = NotificationManagerCompat.from(applicationContext)
        manager.notify(NOTIFICATION_ID, builder.build())


        Log.d("Worker", "Menjalankan proses background..")
        return Result.success()
    }
    private fun getPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TASK
        var flags = PendingIntent.FLAG_UPDATE_CURRENT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flags = flags or PendingIntent.FLAG_IMMUTABLE
        }
        return PendingIntent.getActivity(context, 0, intent, flags)
    }
}