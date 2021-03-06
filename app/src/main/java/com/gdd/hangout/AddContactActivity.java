package com.gdd.hangout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.gdd.hangout.db.ContactDbHelper;
import com.gdd.hangout.model.Person;

public class AddContactActivity extends AppCompatActivity {

    private Person person;
    private String groupName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = this.getIntent().getExtras();
        person = bundle.getParcelable("person");

        TextView personName =  (TextView) findViewById(R.id.textView2);
        personName.setText(person.getName());

        TextView personNumber =  (TextView) findViewById(R.id.textView5);
        personNumber.setText(person.getPhoneNumber());
    }

    public void addContact(View view){

        ContactDbHelper contactDbHelper = new ContactDbHelper(this);
        Bundle bundle = this.getIntent().getExtras();
        person = bundle.getParcelable("person");
        groupName = bundle.getString("groupName");
        System.out.println("Group Name :" + groupName);

        TextView name =  (TextView) findViewById(R.id.textView2);
        TextView phoneNumber =  (TextView) findViewById(R.id.textView5);
        TextView street =  (TextView) findViewById(R.id.editText);
        TextView city =  (TextView) findViewById(R.id.editText2);
        TextView state =  (TextView) findViewById(R.id.editText3);
        TextView country =  (TextView) findViewById(R.id.editText4);
        TextView zipCode =  (TextView) findViewById(R.id.editText5);
        TextView interest =  (TextView) findViewById(R.id.editText6);
        contactDbHelper.createContact(name.getText().toString(), groupName, phoneNumber.getText().toString(),
                street.getText().toString(), city.getText().toString(),
                state.getText().toString(), country.getText().toString(), zipCode.getText().toString(), interest.getText().toString());
        Intent intent = new Intent(AddContactActivity.this,AddParticipantsActivity.class);
        bundle.putString("groupName", groupName);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
