package com.tupaiaer.webview

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

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
}
