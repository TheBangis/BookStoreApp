package com.example.bookstore

import com.example.bookstore.Model.Books
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookstore.Adapter.BookClickListener
import com.example.bookstore.Adapter.BooksAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    var bookList = ArrayList<Books>()
//    lateinit var ourAdapter: BooksAdapter
//    lateinit var database: FirebaseDatabase
//    lateinit var auth: FirebaseAuth
//    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)


        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.books -> {

                    navController.navigate(R.id.dashboardFragment)

                    true

                }
                R.id.addStory -> {

                    navController.navigate(R.id.addBookActivity)

                    true

                }

                R.id.menu -> {

                    navController.navigate(R.id.menuFragment)

                    true

                }

                else -> false
            }


        }
    }


}

//        var toolbar: Toolbar = findViewById(R.id.toolBar)
//        setSupportActionBar(toolbar)
//        title = "Book Store"
//
//        database = FirebaseDatabase.getInstance()
//        databaseReference = database.getReference("Books")
//        databaseReference.addValueEventListener(object : ValueEventListener{
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                for (i in snapshot.children) {
//                    val books = i.getValue(Books::class.java)
//                    bookList.add(books!!)
//
//                    ourAdapter = BooksAdapter(bookList, this@MainActivity)
//                    booksRv.adapter = ourAdapter
//                    booksRv.layoutManager = GridLayoutManager(this@MainActivity, 2)
//
//
//
//                }
//            }
//        })
//    }
//
//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.nav_menu, menu)
//        return true
//
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId){
//            R.id.addBook ->{
//                var intent = Intent(this, AddBookActivity::class.java)
//                startActivity(intent)
//            }
//
//            R.id.signOut ->{
//
//                auth = FirebaseAuth.getInstance()
//                auth.signOut()
//                val intent = Intent(this, LoginActivity::class.java)
//                startActivity(intent)
//                finish()
//
//            }

//        }
//        return true
//
//    }
//
//    override fun onItemClick(books: Books, position: Int) {
//        Toast.makeText(this, books.bookAuthor, Toast.LENGTH_LONG).show()
//         val intent = Intent(this.applicationContext, BookContentActivity::class.java)
//        //intent.putExtra("author", books.bookAuthor)
//        intent.putExtra("bookName", books.bookName)
//        intent.putExtra("bookAuthor", books.bookAuthor)
//        intent.putExtra("bookDes", books.bookDescription)
//        intent.putExtra("bookImage", books.bookImage)
//        startActivity(intent)
//    }


//}
