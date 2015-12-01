package com.gdd.hangout.db;

import android.provider.BaseColumns;

/**
 * Created by ANANDHAKRISHNAN on 11/29/2015.
 */
public class ContactContract {
    public ContactContract() {}

    public static final String TABLE_NAME = "contactInformation";


    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + ContactContract.TABLE_NAME +
            " ("+ ContactEntry.COLUMN_NAME_CONTACT_NAME + " TEXT NOT NULL," +
            ContactEntry.COLUMN_NAME_CONTACT_NUMBER + " TEXT NOT NULL," +
            ContactEntry.COLUMN_NAME_CONTACT_STREET + " TEXT NOT NULL," +
            ContactEntry.COLUMN_NAME_CONTACT_CITY + " TEXT NOT NULL," +
            ContactEntry.COLUMN_NAME_CONTACT_STATE + " TEXT NOT NULL," +
            ContactEntry.COLUMN_NAME_CONTACT_COUNTRY + " TEXT NOT NULL," +
            ContactEntry.COLUMN_NAME_CONTACT_ZIPCODE + " TEXT NOT NULL," +
            ContactEntry.COLUMN_NAME_CONTACT_INTEREST + " TEXT NOT NULL," +
            ContactEntry.COLUMN_NAME_CONTACT_GROUP_NAME + " TEXT NOT NULL," +
            " CONSTRAINT pk_ContactId PRIMARY KEY ("+ ContactEntry.COLUMN_NAME_CONTACT_GROUP_NAME + "," +
            ContactEntry.COLUMN_NAME_CONTACT_NAME+"))";

    public static abstract class ContactEntry implements BaseColumns {

        public static final String COLUMN_NAME_CONTACT_NAME = "name";
        public static final String COLUMN_NAME_CONTACT_NUMBER = "phoneNumber";
        public static final String COLUMN_NAME_CONTACT_STREET = "street";
        public static final String COLUMN_NAME_CONTACT_CITY = "city";
        public static final String COLUMN_NAME_CONTACT_STATE = "state";
        public static final String COLUMN_NAME_CONTACT_COUNTRY = "country";
        public static final String COLUMN_NAME_CONTACT_ZIPCODE = "zipCode";
        public static final String COLUMN_NAME_CONTACT_INTEREST = "interest";
        public static final String COLUMN_NAME_CONTACT_GROUP_NAME = "groupName";

    }

}
