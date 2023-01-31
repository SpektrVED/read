package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_SPEECH_INPUT = 100;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_speak = findViewById<Button>(R.id.btn_speak)

        btn_speak.setOnClickListener{
            speak();
        }
    }

    private fun speak() {
        val mIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        mIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi")

        try {
            startActivityForResult(mIntent, REQUEST_CODE_SPEECH_INPUT)
            val text = findViewById<TextView>(R.id.text)
            //text.text = 1.toString();

        }
        catch (e: java.lang.Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            val text = findViewById<TextView>(R.id.text)
            //text.text = 0.toString();

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode){
            this.REQUEST_CODE_SPEECH_INPUT -> {
                val text = findViewById<TextView>(R.id.text)
                if (resultCode == 100 || data != null){
                    val res : ArrayList<String> = data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>
                    text.text = res[0]
                }
            }
        }
    }


}