package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.DBOperator;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;
import android.widget.Toast;
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.idObject;

public class TutorActivity extends AppCompatActivity implements OnClickListener {

    Button LoginBtn;
    EditText Username,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);
        LoginBtn = this.findViewById(R.id.LoginButton);
        LoginBtn.setOnClickListener(this);
        Username = this.findViewById(R.id.LoginUsername);
        Password = this.findViewById(R.id.LoginPassword);

        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */


    public void onClick(View v)
    {
        int id=v.getId();
        if(id==R.id.LoginButton) {
            String enteredUsername = Username.getText().toString();
            String enteredPassword = Password.getText().toString();
            String sql = "select loginID from Login where username = '" + enteredUsername + "' and password = '" + enteredPassword + "'";

            DBOperator  op = DBOperator.getInstance();



            Cursor cursor = op.execQuery(sql);

            String userID = "null";

            while (cursor.moveToNext()) {
                userID = cursor.getString(0);
            }



            if (userID == "null") {
                Toast.makeText(this, "error loggin in", Toast.LENGTH_SHORT).show();
            } else{
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

