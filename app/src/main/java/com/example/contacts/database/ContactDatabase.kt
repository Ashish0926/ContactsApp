package com.example.contacts.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contacts.dao.ContactsDao
import com.example.contacts.entity.ContactItems

@Database(entities = [ContactItems::class], version = 1, exportSchema = false)
abstract class ContactDatabase: RoomDatabase() {

    abstract fun getContactsDao(): ContactsDao

    companion object{
        @Volatile
        private var INSTANCE: ContactDatabase? = null

        fun getDatabase(context: Context): ContactDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "contact_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}