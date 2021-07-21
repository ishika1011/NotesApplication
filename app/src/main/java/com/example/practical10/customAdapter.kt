package com.example.practical10

import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*


class customAdapter(var context: Addnotes, private val dataSource: ArrayList<Notes>): BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    //2
    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.notesarray, parent, false)

        val tittle = rowView.findViewById(R.id.textView11) as TextView
        val subtitleTextView = rowView.findViewById(R.id.textView23) as TextView
        val descriptiomTextView = rowView.findViewById(R.id.textView24) as TextView
        val dateandtimetv = rowView.findViewById(R.id.textView26) as TextView
        val reminderTime1 = rowView.findViewById(R.id.textView28) as TextView

        val delbut=rowView.findViewById<ImageButton>(R.id.imbutton2)
        delbut.setOnClickListener(View.OnClickListener {
            dataSource.removeAt(position)
            notifyDataSetChanged()
        })

        val editbutton=rowView.findViewById<ImageButton>(R.id.imbutton1)
        editbutton.setOnClickListener(View.OnClickListener {

            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setTitle("Edit Note")

            val customLayout: View = LayoutInflater.from(context).inflate(R.layout.note_edit, null)
            customLayout.findViewById<TextInputEditText>(R.id.note_tittLe)
            customLayout.findViewById<EditText>(R.id.et1).setText(tittle.text)
            customLayout.findViewById<TextInputEditText>(R.id.note_subTitLe)
            customLayout.findViewById<EditText>(R.id.et2).setText(subtitleTextView.text)
            customLayout.findViewById<TextInputEditText>(R.id.note_description)
            customLayout.findViewById<EditText>(R.id.et3).setText(descriptiomTextView.text)

            val setalarm = customLayout.findViewById<Switch>(R.id.switch1)
            setalarm.isChecked = true
            val timePicker = customLayout.findViewById<TimePicker>(R.id.reminderTime)
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timePicker.hour = Calendar.HOUR
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timePicker.minute = Calendar.MINUTE
            }
            builder.setView(customLayout)

            builder.setPositiveButton("Save", DialogInterface.OnClickListener { dialog, which ->
                val calendar = Calendar.getInstance()
                if (Build.VERSION.SDK_INT >= 23) {
                    calendar[calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH], timePicker.hour, timePicker.minute] =
                        0
                } else {
                    calendar[calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH], timePicker.currentHour, timePicker.currentMinute] =
                        0
                }
                if (setalarm.isChecked) {
                    dataSource[position].tittle =
                        customLayout.findViewById<EditText>(R.id.et1).text.toString()
                    dataSource[position].subtittle =
                        customLayout.findViewById<EditText>(R.id.et2).text.toString()
                    dataSource[position].description =
                        customLayout.findViewById<EditText>(R.id.et3).text.toString()
                    dataSource[position].modificationTime = currentDate.toString()
                    dataSource[position].isreminder = setalarm.isChecked
                    dataSource[position].remindertime = calendar.time.toString()
                    notifyDataSetChanged()
                }
            })
            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
            val dialog: AlertDialog = builder.create()
            dialog.show()
        })

        val recipe = getItem(position) as Notes

        tittle.text = recipe.tittle
        subtitleTextView.text = recipe.subtittle
        descriptiomTextView.text = recipe.description
        dateandtimetv.text=recipe.modificationTime
        reminderTime1.text=recipe.remindertime
        return rowView
    }
}











