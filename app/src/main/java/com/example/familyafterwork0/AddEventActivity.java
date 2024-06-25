package com.example.familyafterwork0;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class AddEventActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextDate;
    private EditText editTextTime;
    private EditText editTextDescription;
    private EditText editTextGuardian;
    private EditText editTextChild;
    private Button btnSaveEvent;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_add_event);

        editTextName = findViewById(R.id.editTextName);
        editTextDate = findViewById(R.id.editTextDate);
        editTextTime = findViewById(R.id.editTextTime);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextGuardian = findViewById(R.id.editTextGuardian);
        editTextChild = findViewById(R.id.editTextChild);
        btnSaveEvent = findViewById(R.id.buttonSave);

        dbHelper = new DatabaseHelper(this);
        btnSaveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editTextName.getText().toString().trim();
                String date = editTextDate.getText().toString().trim();
                String time = editTextTime.getText().toString().trim();
                String description = editTextDescription.getText().toString().trim();
                String guardian = editTextGuardian.getText().toString().trim();
                String child = editTextChild.getText().toString().trim();

                if (name.isEmpty() || date.isEmpty() || time.isEmpty() || description.isEmpty() || guardian.isEmpty() || child.isEmpty()) {
                    Toast.makeText(AddEventActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
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

                long result = db.insert(DatabaseHelper.TABLE_EVENTS, null, values);
                if (result == -1) {
                    Log.e("AddEventActivity", "Failed to insert event");
                } else {
                    Log.d("AddEventActivity", "Event inserted successfully, ID: " + result);
                }
                setResult(RESULT_OK);
                finish();
            }
        });
    }

}
