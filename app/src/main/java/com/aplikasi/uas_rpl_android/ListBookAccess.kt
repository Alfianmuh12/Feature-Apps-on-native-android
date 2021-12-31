package com.aplikasi.uas_rpl_android

import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.cursoradapter.widget.SimpleCursorAdapter

class ListBookAccess : AppCompatActivity() {
    var cursor: Cursor? = null
    var listView: ListView? = null
    var button: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_book_access)

        // declaring listView using findViewById()
        listView = findViewById(R.id.ListView)

        // declaring button using findViewById()
        button = findViewById(R.id.Button)

        // set OnClickListener() to the button
        button!!.setOnClickListener(View.OnClickListener { // calling of getContacts()
            contacts
        })
    }
    // create cursor and query the data

    // data is a array of String type which is
    // used to store Number ,Names and id.
    val contacts: Unit

    // creation of adapter using SimpleCursorAdapter class

        // Calling setAdaptor() method to set created adapter
        get() {

            // create cursor and query the data
            cursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null
            )
            startManagingCursor(cursor)

            // data is a array of String type which is
            // used to store Number ,Names and id.
            val data = arrayOf(
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone._ID
            )
            val to = intArrayOf(android.R.id.text1, android.R.id.text2)

            // creation of adapter using SimpleCursorAdapter class
            val adapter =
                SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, data, to)

            // Calling setAdaptor() method to set created adapter
            listView!!.adapter = adapter
            listView!!.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        }
}