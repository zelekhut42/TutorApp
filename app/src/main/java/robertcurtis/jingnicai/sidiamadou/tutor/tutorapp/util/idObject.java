package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util;

import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.DBOperator;
import android.database.Cursor;

public class idObject {
    private String StudentID;
    private String TutorID;
    //public String UserID;

    public void idObject(String LoginID) {
        String sqlStudent = "select studentID from Student where loginID = " + LoginID;
        String sqlTutor = "select tutorID from Tutor where loginID = " + LoginID;


        Cursor cursor = DBOperator.getInstance().execQuery(sqlStudent);

        StudentID = "null";

        while (cursor.moveToNext()) {
            StudentID = cursor.getString(0);
        }



        Cursor c = DBOperator.getInstance().execQuery(sqlTutor);

        TutorID = "null";

        while (c.moveToNext()) {
            TutorID = c.getString(0);
        }

        //UserID = LoginID;

    }

    public String getStudentID() {
        return StudentID;
    }

    public String getTutorID() {
        return TutorID;
    }



}
