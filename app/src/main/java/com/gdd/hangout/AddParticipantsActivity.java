package com.gdd.hangout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.gdd.hangout.db.ContactDbHelper;
import com.gdd.hangout.model.Contact;
import com.gdd.hangout.model.Person;
import com.gdd.hangout.util.ContactsUtil;

import java.util.ArrayList;
import java.util.List;

public class AddParticipantsActivity extends AppCompatActivity {

    public TextView outputText;
    // List view
    private ListView lv;

    // Listview Adapter
    ArrayAdapter<String> adapter;

    private ListView selectedContacts;
    private ArrayAdapter<String> scViewAdapter;
    private String groupName;
    // Search EditText
    EditText inputSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_participants);
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

        System.out.println(getIntent().getStringExtra("groupName"));
        Bundle bundle = getIntent().getExtras();
        System.out.println(bundle);
        groupName = bundle.getString("groupName");
        System.out.println("Bundle Group Name" + groupName);

        inputSearch = (EditText) findViewById(R.id.inputSearch);
        lv = (ListView)findViewById(R.id.listViewContacts);
        List<String> contacts = ContactsUtil.displayContacts(getContentResolver());
        adapter = new ArrayAdapter<String>(this,R.layout.contacts_list_item,R.id.contact_name, contacts);
        lv.setAdapter(adapter);
        lv.setVisibility(View.GONE);

        //show selected contacts
        selectedContacts = (ListView)findViewById(R.id.selectedContacts);
        scViewAdapter = new ArrayAdapter<String>(this,R.layout.selected_contacts_list_item,R.id.selected_contact_name, ContactsUtil.getContactsForGroup(groupName, this));
        selectedContacts.setAdapter(scViewAdapter);
        selectedContacts.setVisibility(View.VISIBLE);

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                if (0 == cs.length()) {
                    lv.setVisibility(View.GONE);
                } else {
                    AddParticipantsActivity.this.adapter.getFilter().filter(cs);
                    lv.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                final String item = (String) parent.getItemAtPosition(position);
                System.out.println(item);
                Intent intent = new Intent(AddParticipantsActivity.this, AddContactActivity.class);
                Bundle bundle = new Bundle();
                String[] contactName = item.split(":");
                Person person = new Person(contactName[0].trim(), contactName[1].trim(), "", "", "", "", "", null);
                bundle.putParcelable("person", person);
                bundle.putString("groupName", groupName);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);

            }
        });

    }

}

