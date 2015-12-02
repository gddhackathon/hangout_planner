package com.gdd.hangout;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.gdd.hangout.R;
import com.gdd.hangout.util.ContactsUtil;


public class GroupDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String groupName = getIntent().getExtras().getString("groupName");
        setTitle(groupName);
        ActionBar actionBar = (ActionBar) getSupportActionBar();
        //TODO: We need to Get the Image from the Group Table
        actionBar.setIcon(R.drawable.ic_group_white_24dp);

        //TODO: Get the Contacts Based on Group Information
        ListView contactsListView = (ListView) findViewById(R.id.listViewContacts);
        //String groupMembers[] = {"Ganesh ", "Debarshi", " David"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.contacts_list_item,R.id.contact_name, ContactsUtil.getContactsForGroup(groupName,this));
        contactsListView.setAdapter(adapter);

        /*try {
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().
                        findFragmentById(R.id.map)).getMap();
            }
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            Marker TP = googleMap.addMarker(new MarkerOptions().
                    position(latLng).title("latLng"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_group_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_plan_trip) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Plan a Trip");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Do you have destination in mind")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //TODO Provide an UI for the user to Provide Address
                        Intent intent = new Intent(getApplication(), CreateNewPlacesActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //TODO To be implemented later
                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
