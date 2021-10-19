package com.example.contacts.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.entity.ContactItems
import com.example.contacts.ContactViewModel
import com.example.contacts.R

class AddContactActivity : AppCompatActivity() {

    private lateinit var firstName : EditText
    private lateinit var lastName : EditText
    private lateinit var phone : EditText
    private lateinit var email : EditText
    private lateinit var saveBtn: Button
    private lateinit var cancelBtn: Button
    lateinit var viewModel: ContactViewModel
    private var contactId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        firstName = findViewById(R.id.firstName)
        lastName = findViewById(R.id.lastName)
        phone = findViewById(R.id.phoneNum)
        email = findViewById(R.id.emaiL)
        saveBtn = findViewById(R.id.saveBtn)
        cancelBtn = findViewById(R.id.cancelBtn)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            ContactViewModel::class.java)


        val contactType = intent.getStringExtra("contactType")
        if(contactType.equals("Edit")){
            val firstNameTxt = intent.getStringExtra("firstName")
            val lastNameTxt = intent.getStringExtra("lastName")
            val phoneTxt = intent.getStringExtra("phoneNumber")
            val emailTxt = intent.getStringExtra("email")
            contactId = intent.getIntExtra("id", -1)
            firstName.setText(firstNameTxt)
            lastName.setText(lastNameTxt)
            phone.setText(phoneTxt)
            email.setText(emailTxt)
            saveBtn.setText("Update")
        } else {
            saveBtn.setText("SAVE")
        }

        saveBtn.setOnClickListener{
            val firstNameTxt = firstName.text.toString()
            val lastNameTxt = lastName.text.toString()
            val phoneTxt = phone.text.toString()
            val emailTxt = email.text.toString()

            if(contactType.equals("Edit")){
                if(firstNameTxt.isNotEmpty() && phoneTxt.isNotEmpty() && emailTxt.isNotEmpty()){
                    val updatedContact = ContactItems(firstNameTxt, lastNameTxt, phoneTxt, emailTxt)
                    updatedContact.id = contactId
                    viewModel.updateContact(updatedContact)
                    Toast.makeText(this, "Contact Updated", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    this.finish()

                }else{
                    Toast.makeText(this, "First Name, Phone and Email are mandatory fields", Toast.LENGTH_SHORT).show()
                }
            } else {
                if(firstNameTxt.isNotEmpty() && phoneTxt.isNotEmpty() && emailTxt.isNotEmpty()){
                    viewModel.addContact(ContactItems(firstNameTxt, lastNameTxt, emailTxt, phoneTxt))
                    Toast.makeText(this, "Contact Added", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    this.finish()

                }else{
                    Toast.makeText(this, "First Name, Phone and Email are mandatory fields", Toast.LENGTH_SHORT).show()
                }
            }

        }

        cancelBtn.setOnClickListener{
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this@AddContactActivity, MainActivity::class.java))
        finish()
    }

}