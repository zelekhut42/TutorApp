package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.DBOperator;

public class DisplayPersonActivity extends AppCompatActivity {
    private Bundle extras;
    private TextView PersonNeame, PersonEmail, PersonTelephone, PersonMajor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_person);
        PersonNeame = findViewById(R.id.PersonNameTextView);
        PersonEmail = findViewById(R.id.PersonEmailTextView);
        PersonTelephone = findViewById(R.id.PersonTelephoneTextView);
        PersonMajor = findViewById(R.id.PersonMajorTextView);

        Intent i = getIntent();
        extras = i.getExtras();


        if (!"null".equals(extras.getString("StudentID"))) {
            String sql = "select Student.student_FName, Student.student_LName, Student.student_Email, Student.student_tel, Major.major_Name from Student left join Major on Student.majorID=Major.majorID where Student.studentID=" + extras.getString("StudentID");

            DBOperator op = DBOperator.getInstance();



            Cursor cursor = op.execQuery(sql);


            while(cursor.moveToNext()) {
                String Major = cursor.getString(4);
                String PhoneNum = cursor.getString(3);

                if (PhoneNum == null) {
                    PhoneNum = "none listed";
                }

                if (Major == null) {
                    Major = "none listed";
                }

                PersonNeame.setText(cursor.getString(0) + " " +cursor.getString(1));
                PersonEmail.setText(cursor.getString(2));
                PersonTelephone.setText(PhoneNum);
                PersonMajor.setText(Major);
            }
        } else if (!"null".equals(extras.getString("TutorID"))) {
            String sql = "select Tutor.tutor_FName, Tutor.tutor_LName, Tutor.tutor_Email, Tutor.tutor_tel, Major.major_Name from Tutor left join Major on Tutor.majorID=Major.majorID where Tutor.tutorID=" + extras.getString("TutorID");

            DBOperator op = DBOperator.getInstance();



            Cursor cursor = op.execQuery(sql);


            while(cursor.moveToNext()) {
                String Major = cursor.getString(4);
                String PhoneNum = cursor.getString(3);

                if (PhoneNum == null) {
                    PhoneNum = "none listed";
                }

                if (Major == null) {
                    Major = "none listed";
                }

                PersonNeame.setText(cursor.getString(0) + " " +cursor.getString(1));
                PersonEmail.setText(cursor.getString(2));
                PersonTelephone.setText(PhoneNum);
                PersonMajor.setText(Major);
            }
        } else {
            PersonNeame.setText("Something went very wrong");
        }





    }

}
