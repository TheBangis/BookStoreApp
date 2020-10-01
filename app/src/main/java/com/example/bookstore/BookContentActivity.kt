package com.example.bookstore

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import coil.load
import com.example.bookstore.Model.Books
import kotlinx.android.synthetic.main.activity_book_content.*
import kotlinx.android.synthetic.main.books_layout.*
import kotlinx.android.synthetic.main.books_layout.bookImage

class BookContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_content)

      val bookname: String? =   intent.getStringExtra("bookName") ?: "zero to one"
        val bookauthor: String? = intent.getStringExtra("bookAuthor") ?: "peter theil"
        val bookDes: String? = intent.getStringExtra("bookDes") ?: "Business idea is always crazy"
        val bookimage : String? = intent.getStringExtra("bookImage")

        bookTitle.text = bookname
        bookAuthor.text = bookauthor
        bookDescription.text = bookDes
        bookImag.load(Uri.parse(bookimage))

    }

}
