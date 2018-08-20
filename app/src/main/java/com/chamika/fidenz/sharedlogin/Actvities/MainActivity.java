package com.chamika.fidenz.sharedlogin.Actvities;

import android.app.Activity;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chamika.fidenz.sharedlogin.R;
import com.chamika.fidenz.sharedlogin.SharedPreference;

import java.nio.BufferUnderflowException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etName,etPassword;
    private Button btnLogin;

    private String sharedName,sharedpasssword,sharedemail,sharedloogingstatus;


    private SharedPreference sharedPreference;

    Activity context = this;

    private String key="yes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etPassword=(EditText)findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        Validation(etName.getText().toString(),etPassword.getText().toString());

        sharedPreference = new SharedPreference();

        sharedName=sharedPreference.getValuename(context);
        sharedpasssword =sharedPreference.getValuepassword(context);
        sharedemail = sharedPreference.getValueemail(context);
        sharedloogingstatus = sharedPreference.getLogging(context);


        btnLogin.setOnClickListener(this);


    }

    @Override
    protected void onResume() {

        if(key.equals(sharedloogingstatus)){

            Intent intent = new Intent(MainActivity.this,Home.class);
            startActivity(intent);

        }

        super.onResume();
    }




    public void registration(View view) {


        Intent intent = new Intent(MainActivity.this,Registration.class);
        startActivity(intent);
    }

    public void Validation(String username,String Password){

        if (username.equalsIgnoreCase(sharedName) && Password.equalsIgnoreCase(sharedpasssword)){

            Intent intent = new Intent(MainActivity.this,Home.class);
            startActivity(intent);

            sharedPreference.iflogged(context,"yes");


        }else {

            Toast.makeText(MainActivity.this,"Invalid user",Toast.LENGTH_LONG).show();

        }


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:

                Validation(etName.getText().toString(),etPassword.getText().toString());

                break;

        }
    }
}
