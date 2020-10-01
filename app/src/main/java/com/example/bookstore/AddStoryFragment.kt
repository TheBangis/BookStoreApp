package com.example.bookstore

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.example.bookstore.Model.Books
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_add_story.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class AddStoryFragment : Fragment() {

    private lateinit var titleEditText: TextInputLayout
    private lateinit var authorEditText: TextInputLayout
    private lateinit var descriptionEditText: TextInputLayout
    private var selectedImage: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_story, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleEditText = requireView().findViewById(R.id.bookTitle)
        authorEditText = requireView().findViewById(R.id.bookAuthor)
        descriptionEditText = requireView().findViewById(R.id.bookDescription)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setButton()
    }

    private fun setButton(){

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


                Toast.makeText(this.context, "Added Successful", Toast.LENGTH_LONG).show()
            }

    }


}
