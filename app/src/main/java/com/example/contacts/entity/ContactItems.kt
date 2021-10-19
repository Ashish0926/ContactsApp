package com.example.contacts.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_items")
data class ContactItems (

    @ColumnInfo(name = "firstName")
    var firstName: String,

    @ColumnInfo(name = "lastName")
    var lastName: String,

    @ColumnInfo(name = "emailId")
    var emailId: String,

    @ColumnInfo(name = "phoneNumber")
    var phoneNumber: String,

    ){
    @PrimaryKey(autoGenerate = true)
    var id = 0

}