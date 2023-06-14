package org.d3if3019.modulo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI


class MainActivity : AppCompatActivity() {
    companion object {
        const val CHANNEL_ID = "updater"
        const val PERMISSION_REQUEST_CODE = 11
    }

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = getString(R.string.channel_desc)
            val manager = getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager?
            manager?.createNotificationChannel(channel)
        }

        navController = findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

        Log.i("MainActivity","onCreate dilakukan")
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart dilakukan")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "onResume dilakukan")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "onPause dilakukan")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "onStop dilakukan")
    }

    override fun onDestroy() {
        Log.i("MainActivity", "onDestroy dilakukan")
        super.onDestroy()
    }

}