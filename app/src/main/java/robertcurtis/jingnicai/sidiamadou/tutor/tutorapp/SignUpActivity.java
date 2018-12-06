package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.DBOperator;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    Button register_button;
    EditText input_name;
    EditText input_email;
    EditText input_pw;
    EditText input_pw2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //bind stuff all together
        register_button = this.findViewById(R.id.signup_register);
        register_button.setOnClickListener(this);

        input_name = this.findViewById(R.id.signup_input_username);
        input_name.setOnClickListener(this);

        input_email = this.findViewById(R.id.signup_input_email);
        input_email.setOnClickListener(this);

        input_pw = this.findViewById(R.id.signup_input_password);
        input_pw.setOnClickListener(this);

        input_pw2 = this.findViewById(R.id.signup_input_reEnterPassword);
        input_pw2.setOnClickListener(this);


        try {
            DBOperator.copyDB(getBaseContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick(View v) {
        // check validate state
        if (!validate()) {
            onSignupFailed();
            return;
        }

        register_button.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = input_name.getText().toString();
        String email = input_email.getText().toString();
        String password = input_pw.getText().toString();

        // TODO: Implement your own signup logic here.

        try {
            // insert new row
            DBOperator op = DBOperator.getInstance();
            String insert_login_sql = " insert into Login (username, password) values ('%s', %s)";
            insert_login_sql = String.format(insert_login_sql, name, password);
            op.execSQL(insert_login_sql);

            // get new login id
            String get_login_id = " select loginID from Login where username = '%s' ";
            get_login_id = String.format(get_login_id, name);
            Cursor login_id_cursor = op.execQuery(get_login_id);

            String login_id = null;
            if (login_id_cursor.moveToFirst()) {
                if (!login_id_cursor.isAfterLast()) {
                    login_id = login_id_cursor.getString(0);
                }
            }

            progressDialog.dismiss();
            Intent i;
            i = new Intent(this, PersonalInfoActivity.class);
            i.putExtra("REGISTER_EMAIL", email);
            if (login_id != null) {
                i.putExtra("REGISTER_LOGIN_ID", login_id);
            }
            this.startActivity(i);
            finish();
        } catch (Exception e) {
            return;
        }

    }


    public void onSignupSuccess() {
        register_button.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        register_button.setEnabled(true);
    }

    public boolean validate() {
        // function to validate input text for registration
        //todo: make sure content submit consistent with the database design, i.e. username length
        boolean valid = true;

        String name = input_name.getText().toString();
        String email = input_email.getText().toString();
        String password = input_pw.getText().toString();
        String reEnterPassword = input_pw2.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            input_name.setError("at least 3 characters");
            valid = false;
        } else {
            input_name.setError(null);
        }

        //todo: Password strength checker
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            input_email.setError("Please enter a valid email address");
            valid = false;
        } else {
            input_email.setError(null);
        }


        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            input_pw.setError("Email between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            input_pw.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            input_pw2.setError("Password Do not match");
            valid = false;
        } else {
            input_pw2.setError(null);
        }

        DBOperator op = DBOperator.getInstance();

        String username_sql = "select username, student_Email from Login, Student where Login.loginID = Student.loginID and username = '%s'";
        username_sql = String.format(username_sql, name);
        Cursor username_cursor = op.execQuery(username_sql);

        if (username_cursor.getCount() != 0) {
            input_name.setError("Already an user?");
            valid = false;
        } else {
            input_name.setError(null);
        }

        String email_sql = "select username, student_Email from Login, Student where Login.loginID = Student.loginID and student_email = '%s' ";
        email_sql = String.format(email_sql, email);
        Cursor email_cursor = op.execQuery(email_sql);

        if (email_cursor.getCount() != 0) {
            input_email.setError("Please enter another email address");
            valid = false;
        } else {
            input_email.setError(null);
        }

        return valid;
    }

}
