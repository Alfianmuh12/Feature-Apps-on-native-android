package com.aplikasi.uas_rpl_android

import android.content.Intent
import android.os.Bundle
import android.os.storage.StorageManager
import android.provider.Settings
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File


const val CURRENT_STORAGE_SPACE = ".STORAGE"

class ClearCache : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clear_cache)
        var availSpace = File(getExternalFilesDir(null).toString()).freeSpace / (1000*1000)
        findViewById<TextView>(R.id.currentStorageValue).apply {
            text = """$availSpace MB"""
        }
        openSettingsAllFilesAccess(this)
    }

    private fun openNativeFileExplorer(activity: AppCompatActivity) {
        val intent = Intent(StorageManager.ACTION_MANAGE_STORAGE)
        activity.startActivity(intent)
    }

    private fun clearAppCacheFiles(activity: AppCompatActivity) {
        val intent = Intent(StorageManager.ACTION_CLEAR_APP_CACHE)
        activity.startActivity(intent)
    }

    private fun openSettingsAllFilesAccess(activity: AppCompatActivity) {
        val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
        activity.startActivity(intent)
    }


    fun clearCache(view: View) {
        var availSpace = File(getExternalFilesDir(null).toString()).freeSpace / (1000*1000)
        openNativeFileExplorer(this)
        clearAppCacheFiles(this)
        this.cacheDir.deleteRecursively()
    //    deleteCache(this)
        val intentDisplay = Intent(this@ClearCache, DisplayClearedCacheActivity::class.java).apply {
            putExtra(CURRENT_STORAGE_SPACE, availSpace)
        }
        startActivity(intentDisplay)
    }

}