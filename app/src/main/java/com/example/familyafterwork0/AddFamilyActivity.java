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

public class AddFamilyActivity extends AppCompatActivity {

    private EditText editMemberName;
    private EditText editAttendance;
    private EditText editImage;

    private Button btnSaveFamily;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_add_member);

        editMemberName = findViewById(R.id.editMemberName);
        editAttendance = findViewById(R.id.editAttendance);
        editImage = findViewById(R.id.editImage);

        btnSaveFamily = findViewById(R.id.btnSaveFamily);

        dbHelper = new DatabaseHelper(this);
        btnSaveFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editMemberName.getText().toString().trim();
                String attend = editAttendance.getText().toString().trim();
                String image = editImage.getText().toString().trim();

                if (name.isEmpty() || attend.isEmpty() || image.isEmpty() ) {
                    Toast.makeText(AddFamilyActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.COLUMN_MEMBER_NAME, name);
                values.put(DatabaseHelper.COLUMN_ROLE, attend);
                values.put(DatabaseHelper.COLUMN_IMAGE, image);


                long result = db.insert(DatabaseHelper.TABLE_FAMILY, null, values);
                if (result == -1) {
                    Log.e("AddEventActivity", "Failed to insert event");
                } else {
                    Log.d("AddEventActivity", "Event inserted successfully, ID: " + result);
                }
                finish();
            }
        });
    }

}
