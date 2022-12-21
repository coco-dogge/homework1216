package com.example.ch8


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {

    class Data{
        var photo:Int = 5
        var name:String = ""
    }

     class MyAdapter(
         private var data: Array<Data>,
         private var view:Int = 0) : BaseAdapter() {

        init {
            this.data = data
            this.view = view
        }

        override fun getCount(): Int {
            return data.size
        }

        override fun getItem(p0: Int): Any {
            return data[p0]
        }

        override fun getItemId(p0: Int): Long {
            return 0
        }

        override fun getView(position: Int, converView: View?, parent: ViewGroup?): View {

            val converView = LayoutInflater.from(parent?.context).inflate(view,parent,false)
            val name : TextView = converView.findViewById(R.id.name)
            name.text = data[position].name
            val imageView: ImageView = converView.findViewById(R.id.imageView)
            imageView.setImageResource(data[position].photo)

            return converView
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transNameArray = arrayListOf("腳踏車","機車","汽車","巴士")
        val transPhotoIdArray = arrayListOf(R.drawable.trans1,R.drawable.trans2,R.drawable.trans3,R.drawable.trans4)

        val transData = Array(transNameArray.size){Data()}//////////////


        for (i in 0 until transData.size) {
            transData[i] = Data()
            transData[i].name = transNameArray[i]
            transData[i].photo = transPhotoIdArray[i]
        }

         val transAdapter = MyAdapter(transData, R.layout.trans_list)

        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.adapter = transAdapter

        val messageArray = arrayListOf("訊息1", "訊息2", "訊息3", "訊息4", "訊息5", "訊息6")
        val messageAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, messageArray)
        val listView: ListView = findViewById(R.id.listView)
        listView.setAdapter(messageAdapter)

        val cubeeNameArray = arrayOf(
            "哭哭", "發抖", "再見", "生氣", "昏倒", "竊笑",
            "很棒", "你好", "驚嚇", "大笑")
        val cubeePhotoIdArray = intArrayOf(
            R.drawable.cubee1, R.drawable.cubee2,
            R.drawable.cubee3, R.drawable.cubee4, R.drawable.cubee5, R.drawable.cubee6,
            R.drawable.cubee7, R.drawable.cubee8, R.drawable.cubee9, R.drawable.cubee10
        )
        val cubeeData =  Array(cubeeNameArray.size){Data()}
        for (i in cubeeData.indices) {
            cubeeData[i] = Data()
            cubeeData[i].name = cubeeNameArray[i]
            cubeeData[i].photo = cubeePhotoIdArray[i]
        }
        val cubeeAdapter = MyAdapter(cubeeData, R.layout.cubee_list)

        val gridView = findViewById<GridView>(R.id.gridView)
        gridView.adapter = cubeeAdapter
        gridView.numColumns = 3
    }
}