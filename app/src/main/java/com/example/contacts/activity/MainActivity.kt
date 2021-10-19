package com.example.contacts.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.*
import com.example.contacts.databinding.ActivityMainBinding
import com.example.contacts.entity.ContactItems
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ContactItemClickInterface,
    ContactItemClickDeleteInterface {

    private lateinit var binding: ActivityMainBinding
    //private lateinit var recyclerView: RecyclerView
    //private lateinit var addFAB: FloatingActionButtonExpandable
    private lateinit var contactAdapter: ContactRVAdapter
    private lateinit var viewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //recyclerView = findViewById(R.id.recyclerview)
        //ddFAB = findViewById(R.id.fab)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        contactAdapter = ContactRVAdapter(this, this, this)
        binding.recyclerview.adapter = contactAdapter

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            ContactViewModel::class.java)
        viewModel.allContacts.observe(this, Observer{ list->
            list?.let{
                contactAdapter.updateContact(it)
            }
        })

        binding.fab.setOnClickListener{
            val intent = Intent(this@MainActivity, AddContactActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if(dy > 0){
                    fab.collapse()
                } else {
                    fab.expand()
                }
            }
        })

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