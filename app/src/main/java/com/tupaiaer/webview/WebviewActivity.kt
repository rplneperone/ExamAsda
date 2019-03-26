package com.tupaiaer.webview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.content.DialogInterface
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.app.AlertDialog
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_webview.*
import kotlin.system.exitProcess
import android.content.Context.ACTIVITY_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.app.ActivityManager
import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.WindowManager
import android.view.KeyEvent.KEYCODE_HOME


class WebviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        }

        val url: String = intent.getStringExtra("url")

        webView.getSettings().setBuiltInZoomControls(true)
        webView!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView!!.loadUrl("http://" + url)
    }

    override fun onBackPressed() {
//        AlertDialog.Builder(this)
//            .setIcon(android.R.drawable.ic_dialog_alert)
//            .setMessage("Yakin ingin keluar aplikasi?")
//            .setCancelable(false)
//            .setPositiveButton("Ya") { dialog, id -> this@WebviewActivity.finish() }
//            .setNegativeButton("Tidak", null)
        val builder = AlertDialog.Builder(this@WebviewActivity)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setTitle("Keluar Aplikasi")
        builder.setMessage("Yakin ingin keluar aplikasi?")

        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("Ya") { dialog, which ->
            moveTaskToBack(true)
            exitProcess(-1)
        }

        builder.setNegativeButton("Tidak") { dialog, which ->
            null
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onPause() {
        super.onPause()

        val activityManager = applicationContext
            .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        activityManager.moveTaskToFront(taskId, 0)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            val builder = AlertDialog.Builder(this@WebviewActivity)
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setTitle("Keluar Aplikasi")
            builder.setMessage("Yakin ingin keluar aplikasi?")

            // Set a positive button and its click listener on alert dialog
            builder.setPositiveButton("YES") { dialog, which ->
                moveTaskToBack(true)
                exitProcess(-1)
            }

            builder.setNegativeButton("No") { dialog, which ->
                null
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()
            return true
        }
        return super.onKeyDown(keyCode, event);
    }
}
