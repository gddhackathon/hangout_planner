package com.gdd.hangout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ANANDHAKRISHNAN on 11/29/2015.
 */
public class GroupDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "GddHangout.db";

    public GroupDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void createGroup(String groupName) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues groupValues = new ContentValues();
        groupValues.put(GroupContract.GroupEntry.COLUMN_NAME_GROUP_NAME, groupName);
        db.insert(GroupContract.TABLE_NAME, null, groupValues);
        }

    public Group getGroup(String groupName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor groupCursor = db.query(GroupContract.TABLE_NAME,
                null,
                GroupContract.GroupEntry.COLUMN_NAME_GROUP_NAME+ "=?",
                new String[] { groupName },
                null,
                null,
                null);
        groupCursor.moveToNext();
        Group group = new Group();
        group.setGroupName(groupCursor.getString(groupCursor.getColumnIndex(GroupContract.GroupEntry.COLUMN_NAME_GROUP_NAME)));
        groupCursor.close();
        return (group);
    }

    public ArrayList<Group> getGroups()
    {
        ArrayList<Group> groups = new ArrayList<Group>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor groupCursor = db.query(GroupContract.TABLE_NAME, null, null, null, null, null, null);
        while (groupCursor.moveToNext()) {
            Group group = new Group();
            group.setGroupName(groupCursor.getString(groupCursor.getColumnIndex(GroupContract.GroupEntry.COLUMN_NAME_GROUP_NAME)));

            groups.add(group);
        }

        groupCursor.close();

        return (groups);
    }

    public ArrayList<String> getGroupNames()
    {
        ArrayList<String> groupNames = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor groupCursor = db.query(GroupContract.TABLE_NAME, null, null, null, null, null, null);
        while (groupCursor.moveToNext()) {
            Group group = new Group();
            groupNames.add(groupCursor.getString(groupCursor.getColumnIndex(GroupContract.GroupEntry.COLUMN_NAME_GROUP_NAME)));
        }

        groupCursor.close();

        return (groupNames);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(GroupContract.SQL_CREATE_TABLE);
        initializeExampleData(db);
    }

    private void initializeExampleData(SQLiteDatabase db)
    {
        ContentValues firstGroupValues = new ContentValues();
        firstGroupValues.put(GroupContract.GroupEntry.COLUMN_NAME_GROUP_NAME, "GDD");
        db.insert(GroupContract.TABLE_NAME, null, firstGroupValues);

        ContentValues secondGroupValues = new ContentValues();
        secondGroupValues.put(GroupContract.GroupEntry.COLUMN_NAME_GROUP_NAME, "Friends");
        db.insert(GroupContract.TABLE_NAME, null, secondGroupValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(GroupDbHelper.class.getSimpleName(),
                "Upgrading database from version " + oldVersion + " to " + newVersion);
    }

}
