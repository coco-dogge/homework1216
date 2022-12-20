package com.example.homework1216ch9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    private var progressRabbit = 0
    private var progressTurtle = 0

    private lateinit var start :Button
    private lateinit var sb_rabbit :SeekBar
    private lateinit var sb_turtle :SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start= findViewById(R.id.btn_start)
        sb_rabbit= findViewById(R.id.sb_rabbit)
        sb_turtle= findViewById(R.id.sb_turtle)

        start.setOnClickListener {
            start.isEnabled =false
            progressRabbit = 0
            progressTurtle = 0
            sb_rabbit.progress = 0
            sb_turtle.progress = 0
            runRabbit()
            runTurtle()
        }
    }

    private val handler:Handler = Handler(Looper.myLooper()!!, Handler.Callback {
        if(it.what == 1)
            sb_rabbit.progress = progressRabbit
        else if(it.what == 2)
            sb_turtle.progress = progressTurtle

        if(progressRabbit >= 100 && progressTurtle < 100){
            Toast.makeText(this,"兔子勝利",Toast.LENGTH_SHORT).show()
            start.isEnabled = true
        }else if(progressRabbit < 100 && progressTurtle >= 100){
            Toast.makeText(this,"烏龜勝利",Toast.LENGTH_SHORT).show()
            start.isEnabled = true
        }
        return@Callback false
    })


   private fun runRabbit(){
        Thread{

            val sleepProbability = arrayListOf(true,true,false)

            while (progressRabbit <= 100 && progressTurtle < 100){
                try {
                    Thread.sleep(100)
                    if (sleepProbability[Random.nextInt(0..2)])
                        Thread.sleep(300)
                }catch(e:InterruptedException){
                    e.printStackTrace()
                }
                progressRabbit += 3
                val msg = Message()
                msg.what = 1
                handler.sendMessage(msg)
            }


        }.start()
    }

    private fun runTurtle(){
        Thread {
            while (progressRabbit < 100 && progressTurtle <= 100){
                try {
                    Thread.sleep(100)
                }catch(e:InterruptedException){
                    e.printStackTrace()
                }

                progressTurtle += 1
                val msg = Message()
                msg.what = 2
                handler.sendMessage(msg)
            }


        }.start()
    }
}

