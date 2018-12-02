package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.DBOperator;

public class PersonalInfoActivity extends AppCompatActivity implements View.OnClickListener {
    Button complete_info;
    EditText input_firstname;
    EditText input_lastname;
    EditText input_telphone;
    SearchableSpinner input_major;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        complete_info = this.findViewById(R.id.complete_info_button);
        complete_info.setOnClickListener(this);

        input_firstname = this.findViewById(R.id.signup_input_firstname);
        input_firstname.setOnClickListener(this);

        input_lastname = this.findViewById(R.id.signup_input_lastname);
        input_lastname.setOnClickListener(this);

        input_telphone = this.findViewById(R.id.signup_input_phonenumber);
        input_telphone.setOnClickListener(this);


        try {
            DBOperator.copyDB(getBaseContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SearchableSpinner searchableSpinner = (SearchableSpinner) findViewById(R.id.signup_major);
        ArrayList<String> marjor_list = get_majors();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(PersonalInfoActivity.this, android.R.layout.simple_spinner_dropdown_item, marjor_list);
        searchableSpinner.setAdapter(arrayAdapter);

        searchableSpinner.setTitle("Select Item");
        searchableSpinner.setPositiveButton("OK");
    }


    public void onClick(View v) {
        String email = getIntent().getStringExtra("REGISTER_EMAIL");
        String login_id = getIntent().getStringExtra("REGISTER_LOGIN_ID");

        String firstname = input_firstname.getText().toString();
        String lastname = input_lastname.getText().toString();
        String telephone = input_telphone.getText().toString();
        String major = input_major.getSelectedItem().toString();

        DBOperator op = DBOperator.getInstance();
        String sql = "select majorID from Major where Major_Name = '%s'";
        sql = String.format(sql, major);
        Cursor cursor = op.execQuery(sql);
        String major_id = null;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                major_id = cursor.getString(0);
                cursor.moveToNext();
            }
        }

        String insert_sql = "insert into Student (student_FName, student_LName, student_Email, student_tel, majorID, loginID) values ('%s', '%s', '%s', '%s', %i, %i)";
        insert_sql = String.format(insert_sql, firstname, lastname, email, telephone, Integer.valueOf(major_id), Integer.valueOf(login_id));

        try {
            op.execSQL(insert_sql);
            Intent i = new Intent(this, LandingPageActivity.class);
            this.startActivity(i);
            finish();
        } catch (Exception e) {
            finish();
        }

    }

    public ArrayList<String> get_majors() {
        // get all the major from the database to fill the searchable spinner
        DBOperator op = DBOperator.getInstance();
        String marjor_sql = "select distinct Major_Name from Major";
        Cursor cursor = op.execQuery(marjor_sql);
        ArrayList<String> ans = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(0);
                ans.add(name);
                cursor.moveToNext();
            }
        }
        return ans;
    }

    public boolean validate() {
        // function to validate input text for registration
        //todo: make sure content submit consistent with the database design, i.e. username length
        boolean valid = true;

        String firstname = input_firstname.getText().toString();
        String lastname = input_lastname.getText().toString();
        String telephone = input_telphone.getText().toString();
        String major = input_major.getSelectedItem().toString();


        if (firstname.isEmpty()) {
            input_firstname.setError("Please enter your first name!");
            valid = false;
        } else {
            input_firstname.setError(null);
        }

        if (lastname.isEmpty()) {
            input_lastname.setError("Please enter your first name!");
            valid = false;
        } else {
            input_lastname.setError(null);
        }


        if (major.isEmpty()) {
            valid = false;
        }

        return valid;
    }


}
