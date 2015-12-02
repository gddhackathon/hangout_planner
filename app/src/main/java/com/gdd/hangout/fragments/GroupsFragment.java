package com.gdd.hangout.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gdd.hangout.GroupDetails;
import com.gdd.hangout.R;
import com.gdd.hangout.db.GroupDbHelper;

import java.util.ArrayList;


public class GroupsFragment extends Fragment {

    public GroupsFragment() {
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
        View rootView =  inflater.inflate(R.layout.fragment_groups, container, false);
        //TODO Retrieve From Database and Below is a Temporary Code

        ListView groupsListView = (ListView)rootView.findViewById(R.id.listViewGroups);
        String groupNames[]= getGroupNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.group_item,R.id.group_name, groupNames);
        groupsListView.setAdapter(adapter);
        onclickGroupListItem(groupsListView);

        return  rootView;
    }

    private String[] getGroupNames(){
        GroupDbHelper groupDbHelper = new GroupDbHelper(getActivity());
        ArrayList<String> groupNames = groupDbHelper.getGroupNames();
        return groupNames.toArray(new String[groupNames.size()]);
    }
    private void onclickGroupListItem(ListView groupsListView) {
        groupsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),GroupDetails.class);
                intent.putExtra("groupName", av.getItemAtPosition(i).toString());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }


}

