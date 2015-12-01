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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gdd.hangout.db.GroupDbHelper;
import com.gdd.hangout.util.ContactsUtil;

import java.util.ArrayList;
import java.util.List;

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

        //TODO Retrieve From Database and Below is a Temporary Code
        ListView groupsListView = (ListView)findViewById(R.id.listViewGroups);
        String groupNames[]= getGroupNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.group_item,R.id.group_name, groupNames);
        groupsListView.setAdapter(adapter);

        //TODO Onclick Of Group List item
        onclickGroupListItem(groupsListView);
    }

    private void onclickGroupListItem(ListView groupsListView) {
        groupsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                Intent intent = new Intent(getApplication(),GroupDetails.class);
                intent.putExtra("groupName", av.getItemAtPosition(i).toString());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
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
        ListView lvContacts = (ListView)findViewById(R.id.listContacts);
        List<String> contacts = ContactsUtil.displayContacts(getContentResolver());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.contacts_list_item,R.id.contact_name, contacts);
        lvContacts.setAdapter(adapter);

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
        ListView listView = (ListView)findViewById(R.id.listContacts);
        listView.setVisibility(View.GONE);
    }
    private void showContactsList() {
        ListView listView = (ListView)findViewById(R.id.listContacts);
        listView.setVisibility(View.VISIBLE);
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
