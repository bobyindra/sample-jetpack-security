package me.bobyindra.android.samplejetpacksecurity.preference

import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import me.bobyindra.android.samplejetpacksecurity.JetpackSecurityApplication
import me.bobyindra.android.samplejetpacksecurity.helper.MyMasterKey

/**
 * Created by Boby-IP on December 05, 2019
 */
class UserSharedPreference {
    companion object {
        const val USER_EMAIL_KEY = "userEmail"

        private val instance by lazy {
            UserSharedPreference()
        }

        fun get(): UserSharedPreference = instance
    }

    private val pref = EncryptedSharedPreferences.create(
        "server_secret_prefs",
        MyMasterKey.getMasterKey(),
        JetpackSecurityApplication.get(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun setUserEmail(email: String) {
        pref.edit {
            putString(USER_EMAIL_KEY, email)
            commit()
        }
    }

    fun getUserEmail(): String? {
        return pref.getString(USER_EMAIL_KEY, "")
    }
}