package com.gdd.hangout;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.Toast;

import com.gdd.hangout.util.ContactsUtil;
import com.gdd.hangout.util.GooglePlacesUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class GroupDetails extends AppCompatActivity implements AdapterView.OnItemClickListener {

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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.contacts_list_item, R.id.contact_name, ContactsUtil.getContactsForGroup(groupName, this));
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
            // custom dialog
            showPopUp();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showPopUp() {
        final Dialog dialog = buildDialogPopUp();
        autoCompletePlaces(dialog);
        checkBoxDestinationCheck(dialog);
        showPlacesButton(dialog);
        dialog.show();
    }

    @NonNull
    private Dialog buildDialogPopUp() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        dialog.setContentView(R.layout.plan_trip_popup);
        dialog.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title_destination_popup);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return dialog;
    }

    private void checkBoxDestinationCheck(final Dialog dialog) {
        CheckBox checkBox = (CheckBox) dialog.findViewById(R.id.checkBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCompleteTextView autoCompView = (AutoCompleteTextView) dialog.findViewById(R.id.autoCompleteTextView);
                if (((CheckBox) v).isChecked()) {
                    autoCompView.setVisibility(View.VISIBLE);
                    autoCompView.setEnabled(true);
                } else {
                    autoCompView.setVisibility(View.GONE);
                    autoCompView.setEnabled(true);
                }
            }
        });
    }

    private void showPlacesButton(Dialog dialog) {
        Button button = (Button) dialog.findViewById(R.id.buttonShowPlaces);
        final AutoCompleteTextView autoCompView = (AutoCompleteTextView) dialog.findViewById(R.id.autoCompleteTextView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), CreateNewPlacesActivity.class);
                intent.putExtra("userDestination", autoCompView.getText().toString());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void autoCompletePlaces(Dialog dialog) {
        AutoCompleteTextView autoCompView = (AutoCompleteTextView) dialog.findViewById(R.id.autoCompleteTextView);
        autoCompView.setAdapter(new GooglePlacesUtil(this, R.layout.places_list_item));
        autoCompView.setOnItemClickListener(this);
    }



    public void onItemClick(AdapterView adapterView, View view, int position, long id) {
        String str = (String) adapterView.getItemAtPosition(position);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
