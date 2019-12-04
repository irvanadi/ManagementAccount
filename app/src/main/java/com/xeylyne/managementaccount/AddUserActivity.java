package com.xeylyne.managementaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddUserActivity extends AppCompatActivity {

    EditText username, password, phone;
    DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        username = findViewById(R.id.edUsername);
        password = findViewById(R.id.edPassword);
        phone = findViewById(R.id.edPhoneNumber);

        dataHelper = new DataHelper(this);

        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean inserted = dataHelper.Register(username.getText().toString(), password.getText().toString(), phone.getText().toString());
                if (inserted){
                    Toast.makeText(AddUserActivity.this, "Data has been Inserted", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddUserActivity.this, DashboardActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(AddUserActivity.this, "Data May Error While Insert", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
