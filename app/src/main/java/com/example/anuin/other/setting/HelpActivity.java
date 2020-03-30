package com.example.anuin.other.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.anuin.R;
import com.example.anuin.other.model.HelpModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HelpActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;

    private ExpandableListAdapter expandableListAdapter;

    private List<HelpModel.DATABean> listDataGroup;

    private HashMap<Integer, List<HelpModel.DATABean>> listDataChild;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        toolbar = findViewById(R.id.toolbarSetting);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Help");

        // initializing the views
        initViews();

        // initializing the listeners
        initListeners();

        // initializing the objects
        initObjects();

        // preparing list data
        initListData();
    }
    private void initViews() {

        expandableListView = findViewById(R.id.expandableListView);

    }

    /**
     * method to initialize the listeners
     */
    private void initListeners() {

        // ExpandableListView on child click listener
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        listDataGroup.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataGroup.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

        // ExpandableListView Group expanded listener
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataGroup.get(groupPosition) + " " + getString(R.string.text_collapsed),
                        Toast.LENGTH_SHORT).show();
            }
        });

        // ExpandableListView Group collapsed listener
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataGroup.get(groupPosition) + " " + getString(R.string.text_collapsed),
                        Toast.LENGTH_SHORT).show();

            }
        });

    }

    /**
     * method to initialize the objects
     */
    private void initObjects() {

        // initializing the list of groups
        listDataGroup = new ArrayList<>();

        // initializing the list of child
        listDataChild = new HashMap<>();

        // initializing the adapter object
        /*expandableListViewAdapter = new ExpandableListAdapter(this, listDataGroup, listDataChild);*/
        /*expandableListAdapter = new com.example.anuin.other.adapter.ExpandableListAdapter(this,listDataGroup, listDataChild);*/

        // setting list adapter
        expandableListView.setAdapter(expandableListAdapter);

    }

    /*
     * Preparing the list data
     *
     * Dummy Items
     */
    private void initListData() {




        // Adding group data
       /* listDataGroup.add(getString(R.string.text_alcohol));
        listDataGroup.add(getString(R.string.text_coffee));
        listDataGroup.add(getString(R.string.text_pasta));
        listDataGroup.add(getString(R.string.text_cold_drinks));*/

        // array of strings
        String[] array;

        // list of alcohol
        List<String> alcoholList = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_alcohol);
        for (String item : array) {
            alcoholList.add(item);
        }

        // list of coffee
        List<String> coffeeList = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_coffee);
        for (String item : array) {
            coffeeList.add(item);
        }

        // list of pasta
        List<String> pastaList = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_pasta);
        for (String item : array) {
            pastaList.add(item);
        }

        // list of cold drinks
        List<String> coldDrinkList = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_cold_drinks);
        for (String item : array) {
            coldDrinkList.add(item);
        }

        // Adding child data
       /* listDataChild.put(listDataGroup.get(0), alcoholList);
        listDataChild.put(listDataGroup.get(1), coffeeList);
        listDataChild.put(listDataGroup.get(2), pastaList);
        listDataChild.put(listDataGroup.get(3), coldDrinkList);*/

        // notify the adapter

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
