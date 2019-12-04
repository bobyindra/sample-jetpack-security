package me.bobyindra.android.samplejetpacksecurity.preference

import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import me.bobyindra.android.samplejetpacksecurity.JetpackSecurityApplication
import me.bobyindra.android.samplejetpacksecurity.helper.MyMasterKey

/**
 * Created by Boby-IP on December 05, 2019
 */
class ServerSharedPreference {
    companion object {
        const val CLIENT_ID = "clientId"
        const val CLIENT_SECRET = "clientSecret"

        private val instance by lazy {
            ServerSharedPreference()
        }

        fun get(): ServerSharedPreference = instance
    }

    private val pref = EncryptedSharedPreferences.create(
        "server_secret_prefs",
        MyMasterKey.getMasterKey(),
        JetpackSecurityApplication.get(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun initKeys() {
        pref.edit {
            // Update Secret Value
            putString(CLIENT_ID, "test1234ClientID")
            putString(CLIENT_SECRET, "test5678ClientSECRET")
            commit()
        }
    }

    fun getClientID(): String {
        return pref.getString(CLIENT_ID, "").toString()
    }

    fun getServerID(): String {
        return pref.getString(CLIENT_SECRET, "").toString()
    }
}