package com.xeylyne.managementaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, phone;
    DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.edUsername);
        password = findViewById(R.id.edPassword);
        phone = findViewById(R.id.edPhoneNumber);

        dataHelper = new DataHelper(this);


        findViewById(R.id.btnRegis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean inserted = dataHelper.Register(username.getText().toString(), password.getText().toString(), phone.getText().toString());
                if (inserted){
                    Toast.makeText(RegisterActivity.this, "Data has been Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Data May Error While Insert", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
