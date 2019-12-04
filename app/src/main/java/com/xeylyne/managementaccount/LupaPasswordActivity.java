package com.xeylyne.managementaccount;

import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LupaPasswordActivity extends AppCompatActivity {

    EditText editText;
    DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);

        editText = findViewById(R.id.edPhoneNumber);
        dataHelper = new DataHelper(this);

        findViewById(R.id.btnLupas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
//                smsIntent.setData(Uri.parse("smsto:"));
//                smsIntent.setType("vnd.android-dir/mms-sms");
//                smsIntent.putExtra("address"  , new String (editText.getText().toString()));
//                smsIntent.putExtra("sms_body"  , "Test ");
//                startActivity(smsIntent);

                Cursor res = dataHelper.LupaPassword(editText.getText().toString());
                if (res.getCount() == 0){
                    Toast.makeText(LupaPasswordActivity.this, "No Telpon Anda Salah", Toast.LENGTH_SHORT).show();
                } else {
                    while (res.moveToNext()){
                        String Username = res.getString(0);
                        String Password = res.getString(1);
                        Toast.makeText(LupaPasswordActivity.this, "Silahkan Check SMS Anda :)", Toast.LENGTH_SHORT).show();
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(editText.getText().toString(), null, "Username : " + Username + "\nPassword : " + Password, null, null);
                    }
                }

            }
        });
    }
}
