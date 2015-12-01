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

        Intent intent = getIntent();
        groupName = intent.getStringExtra(CreateNewGroupActivity.GROUP_NAME);

        Bundle bundle = this.getIntent().getExtras();
        person = bundle.getParcelable("person");

        TextView personName =  (TextView) findViewById(R.id.textView2);
        personName.setText(person.getName());

        TextView personNumber =  (TextView) findViewById(R.id.textView5);
        personNumber.setText(person.getPhoneNumber());
    }

    public void addContact(View view){

        Intent intent = getIntent();
        //String groupName = intent.getStringExtra("GROUP_NAME");

        ContactDbHelper contactDbHelper = new ContactDbHelper(this);
        Bundle bundle = this.getIntent().getExtras();
        person = bundle.getParcelable("person");

        TextView name =  (TextView) findViewById(R.id.textView2);
        TextView phoneNumber =  (TextView) findViewById(R.id.textView5);
        TextView street =  (TextView) findViewById(R.id.editText);
        TextView city =  (TextView) findViewById(R.id.editText2);
        TextView state =  (TextView) findViewById(R.id.editText3);
        TextView country =  (TextView) findViewById(R.id.editText5);
        TextView zipCode =  (TextView) findViewById(R.id.editText6);
        TextView interest =  (TextView) findViewById(R.id.editText5);
        contactDbHelper.createContact(name.getText().toString(), "us friends",phoneNumber.getText().toString(),
                street.getText().toString(), city.getText().toString(),
                state.getText().toString(), country.getText().toString(), zipCode.getText().toString(), interest.getText().toString());
        Intent countryEdit = new Intent(AddContactActivity.this,AddParticipantsActivity.class);
    }

}
