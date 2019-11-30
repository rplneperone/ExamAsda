package com.tupaiaer.webview

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.KeyEvent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGo.setOnClickListener {
            val intent = Intent(this, WebviewActivity::class.java)
            intent.putExtra("url", etUrl.text.toString())

            if (etUrl.text.isNullOrBlank()) {
                Toast.makeText(this, "Inputkan url atau ip", Toast.LENGTH_LONG)
            } else {
                startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {
//        AlertDialog.Builder(this)
//            .setIcon(android.R.drawable.ic_dialog_alert)
//            .setMessage("Yakin ingin keluar aplikasi?")
//            .setCancelable(false)
//            .setPositiveButton("Ya") { dialog, id -> this@WebviewActivity.finish() }
//            .setNegativeButton("Tidak", null)
        val builder = AlertDialog.Builder(this@MainActivity)
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

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            val builder = AlertDialog.Builder(this@MainActivity)
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
