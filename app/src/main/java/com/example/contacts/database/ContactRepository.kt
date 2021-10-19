package com.example.contacts.database

import androidx.lifecycle.LiveData
import com.example.contacts.dao.ContactsDao
import com.example.contacts.entity.ContactItems

class ContactRepository(private val contactsDao: ContactsDao) {

    val allContacts: LiveData<List<ContactItems>> = contactsDao.getAllContactItems()

    // on below line we are creating an insert method
    // for adding the note to our database.
    suspend fun insert(item: ContactItems) {
        contactsDao.insert(item)
    }

    // on below line we are creating a delete method
    // for deleting our note from database.
    suspend fun delete(item: ContactItems){
        contactsDao.delete(item)
    }

    // on below line we are creating a update method for
    // updating our note from database.
    suspend fun update(item: ContactItems){
        contactsDao.update(item)
    }
}