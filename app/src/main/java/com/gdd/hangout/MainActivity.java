package com.gdd.hangout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gdd.hangout.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        //Retrieve From Database and Below is a Temporary Code
        ListView groupsListView = (ListView)findViewById(R.id.listViewGroups);
        String groupNames[]= getGroupNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.group_item,R.id.group_name, groupNames);
        groupsListView.setAdapter(adapter);
    }

    private String[] getGroupNames(){
        GroupDbHelper groupDbHelper = new GroupDbHelper(this);
        ArrayList<String> groupNames = groupDbHelper.getGroupNames();
        return groupNames.toArray(new String[groupNames.size()]);
    }

    public void onCreateNewGroup(View view){
        Intent intent = new Intent(this, CreateNewGroupActivity.class);
        startActivity(intent);
    }

    public void showContacts(View view){
        hideGroupsList();
        showContactsList();
        TextView tv =(TextView) this.findViewById(R.id.textViewTest);
        tv.setText("-- Under Construction --");
        Toast.makeText(getApplicationContext(),
                "THIS TAB WILL SHOW ALL THE CONTACTS OF PHONE", Toast.LENGTH_LONG).show();
    }

    public void showGroups(View view){
        showGroupsList();
        hideContactsList();
    }

    private void showGroupsList() {
        hideContactsList();
        ListView groupsListView = (ListView)findViewById(R.id.listViewGroups);
        groupsListView.setVisibility(View.VISIBLE);
    }

    private void hideContactsList() {
        TextView textView = (TextView)findViewById(R.id.textViewTest);
        textView.setVisibility(View.GONE);
    }
    private void showContactsList() {
        TextView textView = (TextView)findViewById(R.id.textViewTest);
        textView.setVisibility(View.VISIBLE);
    }

    private void hideGroupsList() {
        ListView groupsListView = (ListView)findViewById(R.id.listViewGroups);
        groupsListView.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_create_group){
            Intent intent = new Intent(this, CreateNewGroupActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
