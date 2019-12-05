package me.bobyindra.android.samplejetpacksecurity.helper

import androidx.security.crypto.EncryptedFile
import me.bobyindra.android.samplejetpacksecurity.JetpackSecurityApplication
import java.io.File

/**
 * Created by Boby-IP on December 05, 2019
 */
object FileEncryption {

    private fun getEncryptedFile(): EncryptedFile {
        val secretFile = File("", "")
        return EncryptedFile.Builder(
            secretFile,
            JetpackSecurityApplication.get(),
            MyMasterKey.getMasterKey(),
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        )
            .setKeysetAlias("file_key") // optional
            .setKeysetPrefName("secret_shared_prefs") // optional
            .build()
    }

    fun writeEncryptedFile() {
        getEncryptedFile().openFileOutput().use { outputStream ->
            // Write data from your encrypted file
        }
    }

    fun readEncryptedFile() {
        getEncryptedFile().openFileInput().use { inputStream ->
            // Read data from your encrypted file
        }
    }
}