package com.example.contacts.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.contacts.entity.ContactItems

@Dao
interface ContactsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: ContactItems)

    @Update
    suspend fun update(item: ContactItems)

    @Delete
    suspend fun delete(item: ContactItems)

    @Query("SELECT * FROM contact_items ORDER BY id ASC")
    fun getAllContactItems() : LiveData<List<ContactItems>>
}