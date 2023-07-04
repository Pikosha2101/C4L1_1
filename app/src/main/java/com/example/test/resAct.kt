package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.test.room.Agent
import com.example.test.room.AppDatabase
import com.example.test.room.Cowork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class resAct : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var et1: EditText
    private lateinit var et2: EditText
    private lateinit var et3: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_res)
        textView = findViewById(R.id.textView)
        et1 = findViewById(R.id.editText)
        et2 = findViewById(R.id.editText2)
        et3 = findViewById(R.id.editText3)
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        val b1 = findViewById<Button>(R.id.button)
        val b2 = findViewById<Button>(R.id.button3)
        val b3 = findViewById<Button>(R.id.button2)

        b1.setOnClickListener {
            coroutineScope.launch {
                mySuspendFunction()
            }
        }
        b2.setOnClickListener {
            coroutineScope.launch {
                mySuspendFunction2()
            }
        }
        b3.setOnClickListener {
            coroutineScope.launch {
                mySuspendFunction3()
            }
        }
    }

    private suspend fun mySuspendFunction() {
        textView.text = ""
        var str = et1.text.toString()
        val id = str.toInt()
        val database = AppDatabase.getInstance(this)
        val agentDao = database.agentDao()
        val count = agentDao.getAgentCheckId(id)
        if (count == 1){
            val res = agentDao.getAgentById(id)
            Log.d("Agents", res.toString())
            textView.text = res.toString()
        }else{
            Toast.makeText(this, "Пользователь не существует!", Toast.LENGTH_LONG).show()
        }
    }

    private suspend fun mySuspendFunction2() {
        textView.text = ""
        var str2 = et2.text.toString()
        val id1 = str2.toInt()
        val database = AppDatabase.getInstance(this)
        val coworkDao = database.coworkDao()
        val count2 = coworkDao.getCoworkCheckId(id1)
        if (count2 == 1){
            val res2 = coworkDao.getCoworkById(id1)
            Log.d("Cowork", res2.toString())
            textView.text = res2.toString()
        }else{
            Toast.makeText(this, "Коворкинг не существует!", Toast.LENGTH_LONG).show()
        }
    }


    private suspend fun mySuspendFunction3() {
        textView.text = ""
        var str3 = et3.text.toString()
        val id2 = str3.toInt()
        val database = AppDatabase.getInstance(this)
        val agentDao = database.agentDao()
        val count3 = agentDao.getAgentCheckId(id2)
        if (count3 == 1){
            agentDao.deleteAgent(id2)
            Toast.makeText(this, "Пользователь удалён!", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Удалять некого!", Toast.LENGTH_LONG).show()
        }
    }

    private suspend fun mySuspendFunction4() {
        val cowork = Cowork(2, "Ул.Пушкина 2", "88005553536", "cowork2@mail.ru")
        val database = AppDatabase.getInstance(this)
        val coworkDao = database.coworkDao()
        coworkDao.insertCowork(cowork)

        val res2 = coworkDao.getCoworkById(2)
        Log.d("Cowork", res2.toString())
    }

    fun but2(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}