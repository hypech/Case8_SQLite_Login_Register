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

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

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
        if (username.isEmpty() || password1.isEmpty() || phonenum.isEmpty()) {
            Snackbar.make(et_rgsEmail, "cannot be empty", Snackbar.LENGTH_LONG).setAnchorView(R.id.et_rgsName).show();
        }else{
            //check two passwords same
            if (password1.equals(password2)) {
                //put username and pwd into SQLite
                mDBOpenHelper.add(username, password2, email, phonenum);
                Snackbar.make(et_rgsEmail, "Succeed! Click back to Login.", Snackbar.LENGTH_LONG).setAnchorView(R.id.et_rgsName).show();
            } else {
                Snackbar.make(et_rgsEmail, "password not match.", Snackbar.LENGTH_LONG).setAnchorView(R.id.et_rgsName).show();
            }
        }
    }
}
