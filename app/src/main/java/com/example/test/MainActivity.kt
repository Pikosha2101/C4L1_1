package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.test.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var log: EditText
    private lateinit var pas: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        log = findViewById(R.id.login)
        pas = findViewById(R.id.password)

        val coroutineScope = CoroutineScope(Dispatchers.Main)
        val b1 = findViewById<Button>(R.id.button)
        b1.setOnClickListener {
            coroutineScope.launch {
                mySuspendFunction()
            }
        }
    }


    private suspend fun mySuspendFunction() {
        val logstr = log.text.toString()
        val passtr = pas.text.toString()
        val database = AppDatabase.getInstance(this)
        val agentDao = database.agentDao()
        val count: Int = agentDao.getAgentCheck(logstr, passtr)
        if (count == 1){
            val intent = Intent(this, resAct::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this, "Пользователь не существует!", Toast.LENGTH_LONG).show()
        }
    }

    fun but2(view: View) {
        val intent = Intent(this, regAct::class.java)
        startActivity(intent)
    }
}