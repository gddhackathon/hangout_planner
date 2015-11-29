package com.gdd.hangout;

import android.provider.BaseColumns;

/**
 * Created by ANANDHAKRISHNAN on 11/29/2015.
 */
public class GroupContract {
    public GroupContract() {}

    public static final String TABLE_NAME = "groupInformation";


    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + GroupContract.TABLE_NAME +
            " ("+ GroupEntry.COLUMN_NAME_GROUP_NAME + " TEXT PRIMARY KEY);";

    public static abstract class GroupEntry implements BaseColumns {

            public static final String COLUMN_NAME_GROUP_NAME = "groupName";
        }
    }
