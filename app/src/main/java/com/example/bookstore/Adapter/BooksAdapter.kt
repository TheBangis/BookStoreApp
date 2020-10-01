package com.example.bookstore.Adapter

import com.example.bookstore.Model.Books
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.bookstore.R
import kotlinx.android.synthetic.main.books_layout.view.*

class BooksAdapter(var items: ArrayList<Books>, var clickListener: BookClickListener): RecyclerView.Adapter<BooksViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        return BooksViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.books_layout, parent, false))
    }


    //TODO: check null Book
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bind(items[position], clickListener  )
        
    }


}

class BooksViewHolder (view: View): RecyclerView.ViewHolder(view){

    val bookName = view.bookTitleText
    val bookAuthor = view.bookAuthorText
    val bookDescription = view.bookDescriptionText
    val bookImage = view.bookImage

    fun bind(books: Books, action: BookClickListener) {

        bookName.text = books.bookName
        bookAuthor.text = books.bookAuthor
        bookDescription.text = books.bookDescription
        //bookImage.setImageURI(Uri.parse(books.bookImage))
        bookImage.load(Uri.parse(books.bookImage))

        bookImage.setOnClickListener {
            action.onItemClick(books, adapterPosition)
        }



    }



}