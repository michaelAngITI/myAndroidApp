package com.example.familyafterwork0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{
    // This is needed for requesting AddEventActivity response
    private static final int ADD_EVENT_REQUEST = 1;

    DatabaseHelper dbHelper;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter<EventAdapter.ViewHolder> adapter;
    Button btnAddEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        //Enable the Up button on the app bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler_view);

        //Set the layout of the items in the RecyclerView
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadEventData();





        btnAddEvent = findViewById(R.id.btnAddEvent);

        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, AddEventActivity.class),ADD_EVENT_REQUEST);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        loadEventData();
    }


    //Inflate the menu_appbar to the layout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_appbar, menu);
        return true;
    }
    //Handle clicks on menu_appbar items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        ConstraintLayout mainLayout = findViewById(R.id.MainLayoutView);

        switch (item.getItemId()) {
            case R.id.menu_main:
                //Send explicit intent to FamilyActivity
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                return true;
            case R.id.menu_family:
                //Send explicit intent to FamilyActivity
                Intent j = new Intent(this, FamilyActivity.class);
                startActivity(j);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadEventData(){
        //Set my Adapter for the RecyclerView
        adapter = new EventAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_EVENT_REQUEST && resultCode == RESULT_OK) {
            // Reload data from the database
            loadEventData();
        }
    }
}