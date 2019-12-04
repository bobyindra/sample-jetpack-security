package me.bobyindra.android.samplejetpacksecurity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedFile
import kotlinx.android.synthetic.main.activity_main.*
import me.bobyindra.android.samplejetpacksecurity.helper.MyMasterKey
import me.bobyindra.android.samplejetpacksecurity.preference.ServerSharedPreference
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        buttonShowClientId.setOnClickListener {
            textResult.text =
                getString(R.string.client_id_result, ServerSharedPreference.get().getClientID())
        }

        buttonShowServerId.setOnClickListener {
            textResult.text =
                getString(R.string.server_id_result, ServerSharedPreference.get().getServerID())
        }
    }

    private fun getEncryptedFile(): EncryptedFile {
        val secretFile = File("", "")
        return EncryptedFile.Builder(
            secretFile,
            applicationContext,
            MyMasterKey.getMasterKey(),
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        )
            .setKeysetAlias("file_key") // optional
            .setKeysetPrefName("secret_shared_prefs") // optional
            .build()
    }

    private fun writeEncryptedFile() {
        getEncryptedFile().openFileOutput().use { outputStream ->
            // Write data from your encrypted file
        }
    }

    private fun readEncryptedFile() {
        getEncryptedFile().openFileInput().use { inputStream ->
            // Read data from your encrypted file
        }
    }
}
