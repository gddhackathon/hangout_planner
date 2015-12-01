package com.gdd.hangout.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gdd.hangout.model.Contact;

import java.util.ArrayList;

/**
 * Created by ANANDHAKRISHNAN on 11/29/2015.
 */
public class ContactDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "GddHangout.db";

    public ContactDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void createContact(String name, String groupName,String phoneNumber,
                            String street, String city, String state, String country, String zipCode, String interest) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contactValues = new ContentValues();
        contactValues.put(ContactContract.ContactEntry.COLUMN_NAME_CONTACT_NAME, name);
        contactValues.put(ContactContract.ContactEntry.COLUMN_NAME_CONTACT_NUMBER, phoneNumber);
        contactValues.put(ContactContract.ContactEntry.COLUMN_NAME_CONTACT_STREET, street);
        contactValues.put(ContactContract.ContactEntry.COLUMN_NAME_CONTACT_CITY, city);
        contactValues.put(ContactContract.ContactEntry.COLUMN_NAME_CONTACT_STATE, state);
        contactValues.put(ContactContract.ContactEntry.COLUMN_NAME_CONTACT_COUNTRY, country);
        contactValues.put(ContactContract.ContactEntry.COLUMN_NAME_CONTACT_ZIPCODE, zipCode);
        contactValues.put(ContactContract.ContactEntry.COLUMN_NAME_CONTACT_INTEREST, interest);
        contactValues.put(ContactContract.ContactEntry.COLUMN_NAME_CONTACT_GROUP_NAME, groupName);
        db.insert(ContactContract.TABLE_NAME, null, contactValues);
    }

    public ArrayList<Contact> getContats(String groupName) {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor contactCursor = db.query(ContactContract.TABLE_NAME,
                null,
                ContactContract.ContactEntry.COLUMN_NAME_CONTACT_GROUP_NAME+ "=?",
                new String[] { groupName },
                null,
                null,
                null);
        contactCursor.moveToNext();
        while (contactCursor.moveToNext()) {
            Contact contact = new Contact();
            contact.setName(contactCursor.getString(contactCursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_NAME_CONTACT_NAME)));
            contact.setPhoneNumber(contactCursor.getString(contactCursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_NAME_CONTACT_NAME)));
            contact.setStreet(contactCursor.getString(contactCursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_NAME_CONTACT_NAME)));
            contact.setCity(contactCursor.getString(contactCursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_NAME_CONTACT_NAME)));
            contact.setState(contactCursor.getString(contactCursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_NAME_CONTACT_NAME)));
            contact.setCountry(contactCursor.getString(contactCursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_NAME_CONTACT_NAME)));
            contact.setZipCode(contactCursor.getString(contactCursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_NAME_CONTACT_NAME)));
            contact.setInterest(contactCursor.getString(contactCursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_NAME_CONTACT_NAME)));
            contact.setGroupName(contactCursor.getString(contactCursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_NAME_CONTACT_NAME)));

            contacts.add(contact);
        }
        return (contacts);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContactContract.SQL_CREATE_TABLE);
        System.out.print("Table created" + ContactContract.SQL_CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(ContactDbHelper.class.getSimpleName(),
                "Upgrading database from version " + oldVersion + " to " + newVersion);
    }
}
