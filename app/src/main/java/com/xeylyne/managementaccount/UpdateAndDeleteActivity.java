package com.xeylyne.managementaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateAndDeleteActivity extends AppCompatActivity {

    EditText username, password, phone;
    DataHelper dataHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete);

        username = findViewById(R.id.edUsername);
        password = findViewById(R.id.edPassword);
        phone = findViewById(R.id.edPhoneNumber);
        dataHelper = new DataHelper(this);

        Intent intent = getIntent();
        final int position = intent.getIntExtra("position",0);
        Log.d("onUpdate", String.valueOf(position));

        findViewById(R.id.btnUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = dataHelper.updateData(position + 1, username.getText().toString(), password.getText().toString(), phone.getText().toString());
                if (result){
                    Toast.makeText(UpdateAndDeleteActivity.this, "Data has been Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateAndDeleteActivity.this, "Error while Updating data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = dataHelper.deleteData(position + 1);
                if (result){
                    Toast.makeText(UpdateAndDeleteActivity.this, "Data Has Been Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateAndDeleteActivity.this, "Error While Deleting Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
