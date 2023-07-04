package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.test.room.Agent
import com.example.test.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class regAct : AppCompatActivity() {


    private lateinit var log: EditText
    private lateinit var email: EditText
    private lateinit var pas1: EditText
    private lateinit var pas2: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)
        log = findViewById(R.id.login1)
        email = findViewById(R.id.editTextTextPersonName)
        pas1 = findViewById(R.id.editTextTextPassword)
        pas2 = findViewById(R.id.editTextTextPassword2)

        val coroutineScope = CoroutineScope(Dispatchers.Main)
        val b1 = findViewById<Button>(R.id.button)
        b1.setOnClickListener {
            coroutineScope.launch {
                mySuspendFunction()
            }
        }
    }
    private suspend fun mySuspendFunction() {
        val database = AppDatabase.getInstance(this)
        val agentDao = database.agentDao()
        val count: Int = agentDao.getAgentCount() + 1
        val login = log.text.toString()
        val mail = email.text.toString()
        val pass1 = pas1.text.toString()
        val pass2 = pas2.text.toString()

        if (pass1 == pass2){
            val agent = Agent(count, login, mail, pass1)

            agentDao.insert(agent)
            val agents = agentDao.getAllAgents()
            Log.d("Agents", agents.toString())
        }else{
            Toast.makeText(this, "Пароли различаются!", Toast.LENGTH_LONG).show()
        }

    }

    fun but2(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}