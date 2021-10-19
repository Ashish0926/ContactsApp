package com.example.contacts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.entity.ContactItems

class ContactRVAdapter(val context: Context,
                       private val contactItemClickInterface: ContactItemClickInterface,
                       private val contactItemClickDeleteInterface: ContactItemClickDeleteInterface
): RecyclerView.Adapter<ContactRVAdapter.ContactViewHolder>() {

    private val contacts = ArrayList<ContactItems>()

    inner class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val contactNameText: TextView = itemView.findViewById(R.id.contactName)
        val emailText: TextView = itemView.findViewById(R.id.email)
        val phoneNumberText: TextView = itemView.findViewById(R.id.phoneNum)
        //val userImg = itemView.findViewById<ImageView>(R.id.userImg)
        val deleteIcon: ImageView = itemView.findViewById(R.id.deleteButton)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        (contacts[position].firstName + " " + contacts[position].lastName).also { holder.contactNameText.text = it }
        holder.emailText.text = contacts[position].emailId
        holder.phoneNumberText.text = contacts[position].phoneNumber

        holder.deleteIcon.setOnClickListener {
            contactItemClickDeleteInterface.onDeleteIconClick(contacts[position])
        }

        holder.itemView.setOnClickListener {
            contactItemClickInterface.onItemClick(contacts[position])
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    fun updateContact(newList: List<ContactItems>){
        contacts.clear()
        contacts.addAll(newList)
        notifyDataSetChanged()
    }
}

interface ContactItemClickInterface{
    fun onItemClick(item: ContactItems)
}

interface ContactItemClickDeleteInterface{
    fun onDeleteIconClick(item: ContactItems)
}