package me.bobyindra.android.samplejetpacksecurity.helper

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.MasterKeys

/**
 * Created by Boby-IP on December 03, 2019
 */
object MyMasterKey {

    fun getMasterKey(): String {
        // Master Key
        return MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    }

    fun getAdvancedMasterKey(): String {
        // Custom Advanced Master Key
        val advancedSpec = KeyGenParameterSpec.Builder(
            "master_key", // keystore alias
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        ).apply {
            setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            setKeySize(256)
            setUserAuthenticationRequired(true)
            setUserAuthenticationValidityDurationSeconds(15)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                setUnlockedDeviceRequired(true)
                setIsStrongBoxBacked(true)
            }
        }.build()

        return MasterKeys.getOrCreate(advancedSpec)
    }
}