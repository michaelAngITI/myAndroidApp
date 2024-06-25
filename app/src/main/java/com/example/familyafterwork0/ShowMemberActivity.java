package com.example.familyafterwork0;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class ShowMemberActivity extends AppCompatActivity {
    public static final String EXTRA_MEMBER_ID = "com.example.familyafterwork0.EVENT_ID";

    private EditText editTextName;
    private EditText editTextRole;
    private EditText editTextImage;

    private Button btnEditMember;
    private Button btnDeleteMember;

    private DatabaseHelper dbHelper;
    private Context context;



    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_show_member);

        int memberID = getIntent().getIntExtra(EXTRA_MEMBER_ID, 0);

        editTextName = findViewById(R.id.editMemberName);
        editTextRole = findViewById(R.id.editAttendance);
        editTextImage = findViewById(R.id.editImage);

        btnEditMember = findViewById(R.id.btnEditMember);
        btnDeleteMember = findViewById(R.id.btnDeleteMember);

        dbHelper = new DatabaseHelper(this);
        Family family = dbHelper.findMember(String.valueOf(memberID));

        editTextName.setText(family.getName());
        editTextRole.setText(family.getRole());
        editTextImage.setText(family.getImg());


        btnEditMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editTextName.getText().toString().trim();
                String role = editTextRole.getText().toString().trim();
                String image = editTextImage.getText().toString().trim();

                if (name.isEmpty() || role.isEmpty() || image.isEmpty()) {
                    Toast.makeText(ShowMemberActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.COLUMN_MEMBER_NAME, name);
                values.put(DatabaseHelper.COLUMN_ROLE, role);
                values.put(DatabaseHelper.COLUMN_IMAGE, image);

                long result = db.update(DatabaseHelper.TABLE_FAMILY, values,"_id" + " = ?",
                        new String[]{String.valueOf(family.getID())});
                if (result == -1) {
                    Log.e("AddFamilyActivity", "Failed to insert Member");
                } else {
                    Log.d("AddFamilyActivity", "Member inserted successfully, ID: " + result);
                }
                finish();
            }
        });

        btnDeleteMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbHelper.deleteMember(String.valueOf(memberID));
                finish();

            }
        });
    }

}
