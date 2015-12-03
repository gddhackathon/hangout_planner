package com.gdd.hangout.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.gdd.hangout.db.ContactDbHelper;
import com.gdd.hangout.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khatravath on 11/29/2015.
 */
public class ContactsUtil {

    public static List<String> displayContacts(ContentResolver contentResolver) {

        List<String> contacts = new ArrayList<String>();
        ContentResolver cr = contentResolver;
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer.parseInt(cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        // Toast.makeText(this, "Name: " + name + ", Phone No: " + phoneNo, Toast.LENGTH_SHORT).show();
                        contacts.add(name + " : " + phoneNo);
                    }
                    pCur.close();
                }
            }
        }
        return contacts;
    }

    public static List<String> getContactsForGroup (String groupName, Activity activity){
        List<String> contactInfo = new ArrayList<String>();
        ContactDbHelper contactDbHelper = new ContactDbHelper(activity);
        List<Contact> contacts = contactDbHelper.getContacts(groupName);
        System.out.println(contacts.toArray().toString());
        for(Contact contact : contacts){
            System.out.println(contact.getName() + " : " + contact.getPhoneNumber());
            contactInfo.add(contact.getName() + " : " + contact.getPhoneNumber());
        }
        return contactInfo;
    }

    public static List<String> getAllSavedContacts(Activity activity){
        List<String> contactInfo = new ArrayList<String>();
        ContactDbHelper contactDbHelper = new ContactDbHelper(activity);
        List<Contact> contacts = contactDbHelper.getAllSavedContats();
        System.out.println(contacts.toArray().toString());
        for(Contact contact : contacts){
            System.out.println(contact.getName() + " : " + contact.getPhoneNumber() + " : " + contact.getGroupName());
            contactInfo.add(contact.getName() + " : " + contact.getPhoneNumber() + " : " + contact.getGroupName());
        }
        return contactInfo;
    }

    public static void insertContacts(Activity activity, List<String> selectedContacts, String groupname){
        ContactDbHelper contactDbHelper = new ContactDbHelper(activity);
        System.out.println("selectedContacts = " + selectedContacts);
        for(String selectedContact : selectedContacts){
            String[] contactName = selectedContact.split(":");
            System.out.println("contactName = " + contactName);
            contactDbHelper.createContact(contactName[0].trim(), groupname, contactName[1].trim(), "", "", "", "", "", "");
        }
    }
}
