package ihor.kalaur.pokedex

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import ihor.kalaur.pokedex.service.background.NotifyWorker
import ihor.kalaur.pokedex.util.Constants.WORK_NAME
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class PokedexApplication : Application() {
    private var activityReferences = 0
    private var isActivityChangingConfigurations = false

    override fun onCreate() {
        super.onCreate()
        Log.DEBUG

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

            override fun onActivityStarted(activity: Activity) {
                if (++activityReferences == 1 && !isActivityChangingConfigurations) {
                    Log.d("WorkManager", "Returning to app - cancelling notification")
                    WorkManager.getInstance(applicationContext).cancelUniqueWork(WORK_NAME)
                }
            }

            override fun onActivityResumed(activity: Activity) {
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStopped(activity: Activity) {
                isActivityChangingConfigurations = activity.isChangingConfigurations
                if (--activityReferences == 0 && !isActivityChangingConfigurations) {
                    Log.d("WorkManager", "Leaving app - scheduling notification")
                    val periodicWorkRequest = PeriodicWorkRequestBuilder<NotifyWorker>(2, TimeUnit.DAYS)
                        .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
                        .build()

                    WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
                        WORK_NAME,
                        ExistingPeriodicWorkPolicy.REPLACE,
                        periodicWorkRequest
                    )
                }
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }

        })
    }
}