package com.aplikasi.uas_rpl_android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class DisplayClearedCacheActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_cleared_cache)

        var availSpace = File(getExternalFilesDir(null).toString()).freeSpace / (1000*1000)

//         Get the Intent that started this activity and extract the string
        val oldStorageSpace = intent.getLongExtra(CURRENT_STORAGE_SPACE, 0)

        val savedSpace = oldStorageSpace - availSpace

        var storageBefore = getString(R.string.storage_before, oldStorageSpace)
        findViewById<TextView>(R.id.textViewPreviousStorage).apply {
            text = storageBefore
        }
        var storageAfter = getString(R.string.storage_after, availSpace)
        findViewById<TextView>(R.id.textViewStorageAfter).apply {
            text = storageAfter
        }
        var textSavedSpace = getString(R.string.congratulations, savedSpace)
        findViewById<TextView>(R.id.textViewCongratulations).apply {
            text = textSavedSpace
        }

        findViewById<TextView>(R.id.updatedCurrentStorageValue).apply {
            text = """$availSpace MB"""
        }
    }


}

