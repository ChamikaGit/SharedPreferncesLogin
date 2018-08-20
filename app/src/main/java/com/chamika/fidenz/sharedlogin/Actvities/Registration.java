package com.chamika.fidenz.sharedlogin.Actvities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chamika.fidenz.sharedlogin.R;
import com.chamika.fidenz.sharedlogin.SharedPreference;

public class Registration extends AppCompatActivity implements View.OnClickListener {


    private EditText etName, etPassword, etemail;
    private Button btnRegister;

    private SharedPreference sharedPreference;

    Activity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etName = (EditText) findViewById(R.id.etRgName);
        etemail = (EditText) findViewById(R.id.etRgemail);
        etPassword = (EditText) findViewById(R.id.etRgPassword);
        btnRegister = (Button) findViewById(R.id.btnregister);

        sharedPreference = new SharedPreference();


        btnRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnregister:

                String data = etName.getText().toString();
                sharedPreference.save(context, etName.getText().toString(), etPassword.getText().toString(), etemail.getText().toString());
                Toast.makeText(Registration.this, "Hello" + " " + etName.getText().toString() + " " + "is Successfully Registered", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Registration.this, MainActivity.class);
                startActivity(intent);



                break;

        }
    }
}
