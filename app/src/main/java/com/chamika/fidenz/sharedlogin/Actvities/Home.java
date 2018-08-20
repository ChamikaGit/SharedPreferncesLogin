package com.chamika.fidenz.sharedlogin.Actvities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.SQLException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chamika.fidenz.sharedlogin.Adapter.Myadapter;
import com.chamika.fidenz.sharedlogin.R;
import com.chamika.fidenz.sharedlogin.SharedPreference;
import com.chamika.fidenz.sharedlogin.database.DBHelper;
import com.chamika.fidenz.sharedlogin.models.Student;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements View.OnClickListener,Myadapter.Listener {

    private Button btnlogout;

    private SharedPreference sharedPreference;

    Activity context = this;

    DBHelper dbHelper;
    List mStudentList1 = new ArrayList<Student>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;


    private EditText editTextfname;
    private EditText editTextlname;
    private EditText editTextaddress;
    private EditText editTextphoneno;
    private Button btnad;
    private Button btnedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


      //  btnlogout = (Button) findViewById(R.id.btnlogout);
        btnad =(Button)findViewById(R.id.btnAdd);

        sharedPreference = new SharedPreference();


      //  btnlogout.setOnClickListener(this);
        btnad.setOnClickListener(this);
      //  btnedit.setOnClickListener(this);



        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DBHelper(this);

        //addStudent();
        mStudentList1 = getAllStudent();
    //   Toast.makeText(this, mStudentList1.get(1).toString(),Toast.LENGTH_LONG).show();

        editTextfname =(EditText) findViewById(R.id.etfname);
        editTextlname =(EditText)findViewById(R.id.etlname);
        editTextaddress =(EditText)findViewById(R.id.etadddress);
        editTextphoneno =(EditText) findViewById(R.id.etphoneno);






        adapter = new Myadapter(mStudentList1,this,this);


        recyclerView.setAdapter(adapter);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            sharedPreference.clearSharedPreference(context);

            Intent intent = new Intent(Home.this,MainActivity.class);
            startActivity(intent);

            Toast.makeText(Home.this,"Clear Shared Data",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {


            case R.id.btnAdd:
                addStudent1();

                break;


            case R.id.btnedit:

                break;


        }
    }




    public void addStudent1(){

        Student student = new Student();

        student.setFirstName(editTextfname.getText().toString());
        student.setLastName(editTextlname.getText().toString());
        student.setMobileNumber(editTextphoneno.getText().toString());
        student.setAddress(editTextphoneno.getText().toString());

        try {
            dbHelper.createOrUpdate(student);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }




    public List getAllStudent() {

        List mStudentList = new ArrayList<>();
        try {
            mStudentList.addAll(dbHelper.getAll(Student.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return mStudentList;
    }


    @Override
    public void onClick(final int position) {

        Toast.makeText(context,String.valueOf(position),Toast.LENGTH_LONG).show();

        final Dialog dialog = new Dialog(Home.this);
        dialog.setContentView(R.layout.dialog_mcustom);
        Button dialogButtonyes = (Button) dialog.findViewById(R.id.btndilogyes);

        Button dilogButtonNo = (Button) dialog.findViewById(R.id.btndilogno);

        final EditText mfName = (EditText)dialog.findViewById(R.id.et1fname);
        final EditText mlName =(EditText)dialog.findViewById(R.id.et1lname);
        final EditText maddress= (EditText)dialog.findViewById(R.id.et1adddress);
        final EditText mphoneno =(EditText)dialog.findViewById(R.id.et1phoneno);

        Student s = (Student) mStudentList1.get(position);

        final int id =s.getId();
        String fname=s.getFirstName();
        String lname =s.getLastName();
        String phoneno =s.getMobileNumber();
        String address = s.getAddress();


        mfName.setText(fname);
        mlName.setText(lname);
        maddress.setText(address);
        mphoneno.setText(phoneno);





        // if button is clicked, close the custom dialog
        dialogButtonyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext()," Saved..!!",Toast.LENGTH_SHORT).show();

/*
                Student s = (Student) mStudentList1.get(position);
                int id =s.getId();
  */



                Student student = new Student();

                student.setId(id);
                student.setFirstName(mfName.getText().toString());
                student.setLastName(mlName.getText().toString());
                student.setMobileNumber(maddress.getText().toString());
                student.setAddress(mphoneno.getText().toString());

                try {
                    dbHelper.createOrUpdate(student);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (java.sql.SQLException e) {
                    e.printStackTrace();
                }

            }
        });

        dilogButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(),"No clicked..!!",Toast.LENGTH_SHORT).show();



            }
        });

        dialog.show();

    }

    @Override
    public void onClicknew(int position) {

        Toast.makeText(context,String.valueOf(position),Toast.LENGTH_LONG).show();

        final Dialog dialog = new Dialog(Home.this);
        dialog.setContentView(R.layout.dialog_custom);
        Button dialogButtonyes = (Button) dialog.findViewById(R.id.btndilogyes);

        Button dilogButtonNo = (Button) dialog.findViewById(R.id.btndilogno);


        final Student s = (Student) mStudentList1.get(position);

        final int id =s.getId();


/*
        mfName.setText(fname);
        mlName.setText(lname);
        maddress.setText(address);
        mphoneno.setText(phoneno);
*/




        // if button is clicked, close the custom dialog
        dialogButtonyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext()," Deleted..!!",Toast.LENGTH_SHORT).show();

/*
                Student s = (Student) mStudentList1.get(position);
                int id =s.getId();
  */



                Student student = new Student();

                student.setId(id);


                try {
                    dbHelper.deleteById(Student.class,id);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (java.sql.SQLException e) {
                    e.printStackTrace();
                }

            }
        });

        dilogButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(),"No clicked..!!",Toast.LENGTH_SHORT).show();



            }
        });

        dialog.show();

    }
}
