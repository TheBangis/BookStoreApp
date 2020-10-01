package com.example.bookstore.Adapter

import android.content.Intent
import android.os.Parcelable
import com.example.bookstore.BookContentActivity
import com.example.bookstore.DashboardFragment
import com.example.bookstore.Model.Books

interface BookClickListener {

    fun onItemClick(books: Books, position: Int)

}