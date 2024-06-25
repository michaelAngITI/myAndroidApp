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

public class FamilyActivity extends AppCompatActivity {
    // This is needed for requesting AddFamilyActivity response
    private static final int ADD_MEMBER_REQUEST = 1;

    DatabaseHelper dbHelper;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter<FamilyAdapter.ViewHolder> adapter;
    Button btnAddFamily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        dbHelper = new DatabaseHelper(this);

        //Enable the Up button on the app bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler_view);

        //Set the layout of the items in the RecyclerView
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadFamilyData();

//        //Set my Adapter for the RecyclerView
//        adapter = new FamilyAdapter();
//        recyclerView.setAdapter(adapter);

        btnAddFamily = findViewById(R.id.btnAddFamilyMember);

        btnAddFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(FamilyActivity.this, AddFamilyActivity.class),ADD_MEMBER_REQUEST);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        loadFamilyData();
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
//        ConstraintLayout mainLayout = findViewById(R.id.FamilyLayoutView);

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
    private void loadFamilyData(){
        //Set my Adapter for the RecyclerView
        adapter = new FamilyAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_MEMBER_REQUEST && resultCode == RESULT_OK) {
            // Reload data from the database
            loadFamilyData();
        }
    }
}
