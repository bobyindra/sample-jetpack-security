package me.bobyindra.android.samplejetpacksecurity

import android.app.Application
import me.bobyindra.android.samplejetpacksecurity.preference.ServerSharedPreference

/**
 * Created by Boby-IP on December 05, 2019
 */
class JetpackSecurityApplication : Application() {

    companion object {
        private lateinit var instance: JetpackSecurityApplication

        fun get(): JetpackSecurityApplication {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        ServerSharedPreference.get().initKeys()
    }

}