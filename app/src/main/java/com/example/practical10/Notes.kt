package com.example.practical10

import java.text.SimpleDateFormat
import java.util.*

class Notes(
    private var id1: Int,
    var tittle: String,
    var subtittle: String,
    var description: String,
    var modificationTime: String,
    var isreminder: Boolean = false,
    var remindertime:String)
{
    var id=noteIDGenerator()

    constructor(note: Notes):
        this(note.id1,note.tittle,note.subtittle,note.description,note.modificationTime,note.isreminder,note.remindertime){
        isreminder=note.isreminder
    }
 companion object{
    var idNote=0
      fun noteIDGenerator(): Int {
          idNote++
          return idNote
     }

     val arrayNote: ArrayList<Notes> = ArrayList<Notes>()
     fun addingarray(id2: Int, str1: String, str2: String, str3: String, str4: String, b: Boolean,str5:String)     {
         noteIDGenerator()
         val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
         val currentDate = sdf.format(Date())
         val a=Notes(id2, str1, str2, str3, str4, true,str5)
         arrayNote.add(a)
     }
     

//     fun getItemPosition(id: Int): Int {
//         for (position in 0 until arrayNote.size)
//             if (arrayNote[position]== arrayNote[id])
//                 return position
//         return 0
//     }
//
//     fun removingele(item:MenuItem) {
//       val info=item.menuInfo as AdapterView.AdapterContextMenuInfo
//       arrayNote.removeAt(info.position)
//        val adapter = customAdapter(this, Notes.arrayNote)
//     }
 }

}

