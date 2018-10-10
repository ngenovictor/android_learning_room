package com.example.listnameapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewUserActivity extends AppCompatActivity {
    private EditText editFirstName;
    private EditText editSecondName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        editFirstName = findViewById(R.id.edit_first_name);
        editSecondName = findViewById(R.id.edit_second_name);
        Button mSaveButton = findViewById(R.id.button_save);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                Editable firstName = editFirstName.getText();
                Editable secondName = editSecondName.getText();

                if(TextUtils.isEmpty(firstName) | TextUtils.isEmpty(secondName)){
                    setResult(RESULT_CANCELED, replyIntent);
                }else{
                    replyIntent.putExtra("USER_FIRST_NAME", firstName.toString());
                    replyIntent.putExtra("USER_SECOND_NAME", secondName.toString());
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
