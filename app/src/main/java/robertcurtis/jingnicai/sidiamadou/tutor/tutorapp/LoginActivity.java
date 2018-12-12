package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.DBOperator;
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.idObject;

public class LoginActivity extends AppCompatActivity implements OnClickListener {

    Button LoginBtn;
    EditText Username, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginBtn = this.findViewById(R.id.LoginButton);
        LoginBtn.setOnClickListener(this);
        Username = this.findViewById(R.id.LoginUsername);
        Password = this.findViewById(R.id.LoginPassword);

        try {
            DBOperator.copyDB(getBaseContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //this is only needed if you have specific things
        //that you want to do when the user presses the back button.
        /* your specific things...*/
        Intent myIntent = new Intent(this, WelcomeActivity.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// clear back stack
        startActivity(myIntent);
        finish();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */


    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.LoginButton) {
            String enteredUsername = Username.getText().toString();
            String enteredPassword = Password.getText().toString();
            String sql = "select loginID from Login where username = '" + enteredUsername + "' and password = '" + enteredPassword + "'";

            DBOperator op = DBOperator.getInstance();


            Cursor cursor = op.execQuery(sql);

            String userID = null;

            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    userID = cursor.getString(0);
                    cursor.moveToNext();
                }
            }


            if (userID == null) {
                Toast.makeText(this, "error loggin in", Toast.LENGTH_SHORT).show();
            } else {
                idObject idobject = new idObject();
                idobject.idObject(userID);
                Bundle extras = new Bundle();
                extras.putString("StudentID", idobject.getStudentID());
                extras.putString("TutorID", idobject.getTutorID());

                //Toast.makeText(this, "StudentID = " + extras.getString("StudentID") + " TutorID = " + extras.getString("TutorID") + " loginID = " + idobject.UserID, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(this, LandingPageActivity.class);
                i.putExtras(extras);
                this.startActivity(i);
                finish();

            }

        }
    }


}


