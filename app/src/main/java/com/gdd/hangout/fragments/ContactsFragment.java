package com.gdd.hangout.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gdd.hangout.R;
import com.gdd.hangout.util.ContactsUtil;

import java.util.List;


public class ContactsFragment extends Fragment{

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);
        ListView lvContacts = (ListView)rootView.findViewById(R.id.listContacts);
        List<String> contacts = ContactsUtil.getAllSavedContacts(getActivity());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.contacts_list_item,R.id.contact_name, contacts);
        lvContacts.setAdapter(adapter);
        return rootView;
    }

}

