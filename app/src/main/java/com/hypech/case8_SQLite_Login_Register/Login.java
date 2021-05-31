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
package com.hypech.case8_SQLite_Login_Register;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Login extends AppCompatActivity {

    DBOpenHelper mDBOpenHelper;
    private EditText etUser, etPWD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDBOpenHelper = new DBOpenHelper(this);

        etUser = findViewById(R.id.et_User);
        etPWD = findViewById(R.id.et_Psw);
    }

    public void clickBack(View view) {
        finish();
    }

    public void clickLogin(View view) {
        //get the information from input
        String name = etUser.getText().toString().trim();
        String password = etPWD.getText().toString().trim();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {

            Cursor cursor = mDBOpenHelper.getOneUser(name, password);

            if (cursor != null && cursor.getCount() > 0) {
                Snackbar.make(etPWD, "Login Succeed!", Snackbar.LENGTH_LONG).setAnchorView(R.id.et_Psw).show();
            } else {
                Snackbar.make(etUser, "use/pwd not match. Try again.", Snackbar.LENGTH_LONG).setAnchorView(R.id.et_User).show();
            }
        }
    }
}