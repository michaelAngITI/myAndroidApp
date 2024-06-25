package com.example.familyafterwork0;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class ShowEventActivity extends AppCompatActivity {
    public static final String EXTRA_EVENT_ID = "com.example.familyafterwork0.EVENT_ID";

    private EditText editTextTitle;
    private EditText editTextDate;
    private EditText editTextTime;
    private EditText editTextDescription;
    private EditText editTextMember;
    private EditText editTextKid;
    private Button btnEditEvent;
    private Button btnDeleteEvent;

    private DatabaseHelper dbHelper;
    private Context context;



    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_show_event);

        int eventID = getIntent().getIntExtra(EXTRA_EVENT_ID, 0);

        editTextTitle = findViewById(R.id.editTextName);
        editTextDate = findViewById(R.id.editTextDate);
        editTextTime = findViewById(R.id.editTextTime);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextMember = findViewById(R.id.editTextGuardian);
        editTextKid = findViewById(R.id.editTextChild);
        btnEditEvent = findViewById(R.id.btnEditEvent);
        btnDeleteEvent = findViewById(R.id.btnDeleteEvent);

        dbHelper = new DatabaseHelper(this);
        Event event = dbHelper.findEvent(String.valueOf(eventID));

        editTextTitle.setText(event.getTitle());
        editTextDate.setText(event.getDate());
        editTextTime.setText(event.getTime());
        editTextDescription.setText(event.getDescr());
        editTextMember.setText(event.getMember());
        editTextKid.setText(event.getKid());

        btnEditEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editTextTitle.getText().toString().trim();
                String date = editTextDate.getText().toString().trim();
                String time = editTextTime.getText().toString().trim();
                String description = editTextDescription.getText().toString().trim();
                String guardian = editTextMember.getText().toString().trim();
                String child = editTextKid.getText().toString().trim();

                if (name.isEmpty() || date.isEmpty() || time.isEmpty() || description.isEmpty() || guardian.isEmpty() || child.isEmpty()) {
                    Toast.makeText(ShowEventActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.COLUMN_TITLE, name);
                values.put(DatabaseHelper.COLUMN_DATE, date);
                values.put(DatabaseHelper.COLUMN_TIME, time);
                values.put(DatabaseHelper.COLUMN_DESCRIPTION, description);
                values.put(DatabaseHelper.COLUMN_MEMBER, guardian);
                values.put(DatabaseHelper.COLUMN_KID, child);

                long result = db.update(DatabaseHelper.TABLE_EVENTS, values,"_id" + " = ?",
                        new String[]{String.valueOf(event.getID())});
                if (result == -1) {
                    Log.e("AddEventActivity", "Failed to insert event");
                } else {
                    Log.d("AddEventActivity", "Event inserted successfully, ID: " + result);
                }
                finish();
            }
        });

        btnDeleteEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbHelper.deleteEvent(String.valueOf(eventID));
                finish();

            }
        });
    }

}
