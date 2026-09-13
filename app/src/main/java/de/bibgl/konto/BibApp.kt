package de.bibgl.konto

import android.app.Application
import de.bibgl.konto.data.Store
import de.bibgl.konto.work.DueDateWorker
import de.bibgl.konto.work.Notifications

class BibApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Notifications.createChannel(this)
        // Nach einem Neustart oder App-Update den taeglichen Check wieder anwerfen.
        val store = Store(this)
        if (store.hasProfiles && store.notificationsEnabled) {
            DueDateWorker.schedule(this)
        }
    }
}
