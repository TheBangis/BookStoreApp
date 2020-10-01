package com.example.bookstore

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookstore.Adapter.BookClickListener
import com.example.bookstore.Adapter.BooksAdapter
import com.example.bookstore.Model.Books
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_dashboard.*

/**
 * A simple [Fragment] subclass.
 */
class DashboardFragment : Fragment(), BookClickListener {

    var bookList = ArrayList<Books>()
    lateinit var ourAdapter: BooksAdapter
    lateinit var database: FirebaseDatabase
    lateinit var auth: FirebaseAuth
    lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("Books")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children) {
                    val books = i.getValue(Books::class.java)
                    bookList.add(books!!)

                    ourAdapter = BooksAdapter(bookList, this@DashboardFragment)
                    booksRv!!.adapter = ourAdapter
                    booksRv!!.layoutManager = GridLayoutManager(context, 2)



                }
            }
        })



    }

    override fun onItemClick(books: Books, position: Int) {
        val intent = Intent(context, BookContentActivity::class.java)
        //intent.putExtra("author", books.bookAuthor)
        intent.putExtra("bookName", books.bookName)
        intent.putExtra("bookAuthor", books.bookAuthor)
        intent.putExtra("bookDes", books.bookDescription)
        intent.putExtra("bookImage", books.bookImage)
        startActivity(intent)
    }


}
