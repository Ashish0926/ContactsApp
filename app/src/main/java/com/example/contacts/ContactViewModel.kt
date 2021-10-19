package com.example.contacts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.contacts.database.ContactDatabase
import com.example.contacts.database.ContactRepository
import com.example.contacts.entity.ContactItems
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(application: Application): AndroidViewModel(application) {

    // on below line we are creating a variable
    // for our all contact list and repository
    val allContacts : LiveData<List<ContactItems>>
    val repository : ContactRepository

    // on below line we are initializing
    // our dao, repository and all contacts
    init {
        val dao = ContactDatabase.getDatabase(application).getContactsDao()
        repository = ContactRepository(dao)
        allContacts = repository.allContacts
    }

    // on below line we are creating a new method for deleting a contact. In this we are
    // calling a delete method from our repository to delete our contact.
    fun deleteContact (item: ContactItems) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(item)
    }

    // on below line we are creating a new method for updating a contact. In this we are
    // calling a update method from our repository to update our contact.
    fun updateContact(item: ContactItems) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(item)
    }


    // on below line we are creating a new method for adding a new contact to our database
    // we are calling a method from our repository to add a new contact.
    fun addContact(item: ContactItems) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(item)
    }
}