package com.ashish.storageclean

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.storage.StorageManager
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.activity_main.toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val cleanStorage = findViewById<View>(R.id.launchSystemClean)
        cleanStorage.setOnClickListener({
            launchIntentForCleanup(this)
        })
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    @TargetApi(26)
    private fun launchIntentForCleanup(activity: AppCompatActivity) {
        val bytesToFree = 4 * 1000 * 1000
        val intent = Intent(StorageManager.ACTION_MANAGE_STORAGE)
        intent.putExtra(StorageManager.EXTRA_REQUESTED_BYTES, bytesToFree)
        activity.startActivityForResult(intent, 5)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.v("ashish", "onActivityResult requestCode= " + requestCode + " resultCode=" + resultCode)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
