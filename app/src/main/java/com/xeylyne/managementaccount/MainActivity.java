package com.xeylyne.managementaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.edUsername);
        password = findViewById(R.id.edPassword);

        dataHelper = new DataHelper(this);


        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = dataHelper.Select(username.getText().toString(), password.getText().toString());
                if (res.getCount() == 0){
                    Toast.makeText(MainActivity.this, "Username/Password Salah", Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(MainActivity.this, "Berhasil Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }
            }
        });

        findViewById(R.id.btnRegis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnLupas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LupaPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
