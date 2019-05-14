package com.andro.assistantlauncher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.ComponentName
import android.util.Log
import android.widget.Toast
import java.lang.Exception

class AssistantLauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
//            val intent = Intent(Intent.ACTION_MAIN)
//            intent.setClassName("com.google.android.googlequicksearchbox",
//                    "com.google.android.apps.gsa.staticplugins.opa.OpaActivity")
//            startActivity(intent)
            startActivity(Intent(Intent.ACTION_VOICE_COMMAND).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            finish()
        } catch (ex: Exception) {
            Log.e("AssistantLaunchException", ex.message)
            Toast.makeText(this, "Unable to start Google Assistant", Toast.LENGTH_LONG).show()
        }
    }
}
