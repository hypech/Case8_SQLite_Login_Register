/*
 * Copyright 2021 The Learning Android with Cases Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hypech.case7full_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private EditText et_rgsName, et_rgsEmail, et_rgsPhoneNum, et_rgsPsw1, et_rgsPsw2;
    private DBOpenHelper mDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mDBOpenHelper = new DBOpenHelper(this);

        et_rgsName     = findViewById(R.id.et_rgsName);
        et_rgsEmail    = findViewById(R.id.et_rgsEmail);
        et_rgsPhoneNum = findViewById(R.id.et_rgsPhoneNum);
        et_rgsPsw1     = findViewById(R.id.et_rgsPsw1);
        et_rgsPsw2     = findViewById(R.id.et_rgsPsw2);
    }

    public void clickBack(View view){
        finish();
    }

    public void clickRegister(View view){
        //get the information from input
        String username = et_rgsName.getText().toString().trim();
        String password1 = et_rgsPsw1.getText().toString().trim();
        String password2 = et_rgsPsw2.getText().toString().trim();
        String email = et_rgsEmail.getText().toString().trim();
        String phonenum = et_rgsPhoneNum.getText().toString().trim();
        //validation
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password1) && !TextUtils.isEmpty(password2)) {
            //check two passwords same
            if (password1.equals(password2)) {
                //put username and pwd into SQLite
                mDBOpenHelper.add(username, password2, email, phonenum);
                Toast.makeText(this, username + " Register successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "two passwords are not same. retry.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "cannot be empty. ", Toast.LENGTH_SHORT).show();
        }
    }
}
