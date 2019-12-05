package me.bobyindra.android.samplejetpacksecurity

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import kotlinx.android.synthetic.main.activity_main.*
import me.bobyindra.android.samplejetpacksecurity.helper.MyMasterKey
import me.bobyindra.android.samplejetpacksecurity.helper.hideKeyboard
import me.bobyindra.android.samplejetpacksecurity.helper.highlight
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initEncryptedSharedPreference()
        initView()
    }

    private fun initView() {
        buttonWriteValue.setOnClickListener {
            writeValue()
        }

        buttonReadValue.setOnClickListener {
            readValue()
        }
    }

    private fun initEncryptedSharedPreference() {
        pref = EncryptedSharedPreferences.create(
            PREF_NAME,
            MyMasterKey.getMasterKey(),
            JetpackSecurityApplication.get(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private fun writeValue() {
        val startTs = System.currentTimeMillis()

        pref.edit {
            putString(DATA_KEY, editInsertValue.text.toString())
            commit()
        }

        val endTs = System.currentTimeMillis()
        textTimeWrite.text = getString(R.string.time_to_write).format(endTs - startTs)

        hideKeyboard()
        showRawFile()
    }

    private fun readValue() {
        val startTs = System.currentTimeMillis()

        val value = pref.getString(DATA_KEY, "")
        textReadValue.text = value

        val endTs = System.currentTimeMillis()
        textTimeRead.text = getString(R.string.time_to_write).format(endTs - startTs)

        hideKeyboard()
        showRawFile()
    }

    private fun showRawFile() {
        val preferencesFile = File("${applicationInfo.dataDir}/shared_prefs/$PREF_NAME.xml")
        if (preferencesFile.exists()) {
            textEncryptedValue.text = preferencesFile.readText().highlight()
        } else {
            textEncryptedValue.text = ""
        }
    }

    companion object {
        const val DATA_KEY = "DATA_KEY"
        const val PREF_NAME = "data_secret_prefs"
    }
}
