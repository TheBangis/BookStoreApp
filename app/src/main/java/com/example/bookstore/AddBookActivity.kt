package com.example.bookstore
//
import com.example.bookstore.Model.Books
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_add_book.*
import java.util.*

class AddBookActivity : AppCompatActivity() {

    lateinit private var titleEditText: TextInputLayout
    lateinit private var authorEditText: TextInputLayout
    lateinit private var descriptionEditText: TextInputLayout
    private var selectedImage: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        imageView.setOnClickListener {
            intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }

        addBookButton.setOnClickListener {

            progressBar.visibility = View.VISIBLE
            uploadImageToStorage()



        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data !=null){
            selectedImage = data.data
            imageView.setImageURI(selectedImage)


        }
    }
    private fun uploadImageToStorage(){
        var filename =  UUID.randomUUID().toString()
        val storageRef = FirebaseStorage.getInstance().getReference("images/$filename")

        storageRef.putFile(selectedImage!!)
            .addOnSuccessListener {

                storageRef.downloadUrl .addOnSuccessListener {
                    Log.d("AddbookActivity", it.toString())

                    saveDataToDB(it.toString())
                }
            }
    }

    private fun saveDataToDB(bookImageUr: String ){
        titleEditText = findViewById(R.id.bookTitle)
        authorEditText = findViewById(R.id.bookAuthor)
        descriptionEditText = findViewById(R.id.bookDescription)


        val title = titleEditText.editText?.text.toString()
        val author = authorEditText.editText?.text.toString()
        val description = descriptionEditText.editText?.text.toString()

        val ref = FirebaseDatabase.getInstance().getReference("Books").push()
        val books = Books(title, author, description, bookImageUr)

        ref.setValue(books).addOnCompleteListener {
//            progressBar.visibility = View.GONE
//            imageButton.setImageResource(R.drawable.ic_file_upload)
//            titleEditText.editText?.text = null
//            authorEditText.editText?.text = null
//            descriptionEditText.editText?.text = null
            var mainActivity = Intent(this, MainActivity::class.java)
            mainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(mainActivity)
            Toast.makeText(this, "Added Successful", Toast.LENGTH_LONG).show()
        }


    }

}
