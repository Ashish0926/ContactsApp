package com.example.contacts.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.*
import com.example.contacts.entity.ContactItems
import com.tuann.floatingactionbuttonexpandable.FloatingActionButtonExpandable


class MainActivity : AppCompatActivity(), ContactItemClickInterface,
    ContactItemClickDeleteInterface {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addFAB: FloatingActionButtonExpandable
    private lateinit var contactAdapter: ContactRVAdapter
    lateinit var viewModel: ContactViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        addFAB = findViewById(R.id.fab)
        recyclerView.layoutManager = LinearLayoutManager(this)

        contactAdapter = ContactRVAdapter(this, this, this)
        recyclerView.adapter = contactAdapter
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            ContactViewModel::class.java)
        viewModel.allContacts.observe(this, Observer{ list->
            list?.let{
                contactAdapter.updateContact(it)
            }
        })
        addFAB.setOnClickListener{
            val intent = Intent(this@MainActivity, AddContactActivity::class.java)
            startActivity(intent)
            this.finish()
        }

    }

    override fun onItemClick(item: ContactItems) {
        val intent = Intent(this@MainActivity, AddContactActivity::class.java)
        intent.putExtra("contactType", "Edit")
        intent.putExtra("firstName", item.firstName)
        intent.putExtra("lastName", item.lastName)
        intent.putExtra("phoneNumber", item.phoneNumber)
        intent.putExtra("email", item.emailId)
        intent.putExtra("id", item.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(item: ContactItems) {
        viewModel.deleteContact(item)
        Toast.makeText(this, "Contact Deleted", Toast.LENGTH_SHORT).show()
    }
}