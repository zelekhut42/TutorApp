package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.DBOperator;
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.MyStudentsAdapter;

public class MyStudentsActivity extends AppCompatActivity {

    private RecyclerView MyStudentsRecyclerView;
    private MyStudentsAdapter MyStudentsAdapter;
    private RecyclerView.LayoutManager MyStudentsLayoutManager;
    private ArrayList<MyStudentsItem> MyStudentsList = new ArrayList<>();

    private String StudentID;
    private String TutorID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_students);

        Intent i = getIntent();
        Bundle extras = i.getExtras();

        StudentID = extras.getString("StudentID");
        TutorID = extras.getString("TutorID");





        String sql = "select Student.student_FName, Student.student_LName, Major.major_Name, Student.studentID, SkillSet.skillID, SkillSet.skill_Name, IsTutoring.StudentHasPassed from Student inner join IsTutoring on Student.studentID=IsTutoring.studentID inner join SkillSet on IsTutoring.skillID=SkillSet.skillID left join Major on Student.majorID=Major.majorID where IsTutoring.tutorID=" + TutorID + ";";


        DBOperator op = DBOperator.getInstance();



        Cursor cursor = op.execQuery(sql);


        while(cursor.moveToNext()) {
            String hasPassed = cursor.getString(6);

            if(hasPassed.equals("0")) {

                try {
                    MyStudentsList.add(new MyStudentsItem(cursor.getString(0) + " " + cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getColumnName(4), cursor.getString(5)));
                } catch (Exception e) {
                    MyStudentsList.add(new MyStudentsItem(cursor.getString(0) + " " + cursor.getString(1), "None Listed", cursor.getString(3), cursor.getColumnName(4), cursor.getString(5)));
                }
            } else {
                //do nothing
            }
        }




        MyStudentsRecyclerView = findViewById(R.id.MyStudentsRecyclerView);
        MyStudentsRecyclerView.setHasFixedSize(true);
        MyStudentsLayoutManager = new LinearLayoutManager(this);
        MyStudentsRecyclerView.setLayoutManager(MyStudentsLayoutManager);



        MyStudentsAdapter = new MyStudentsAdapter(MyStudentsList);


        MyStudentsRecyclerView.setAdapter(MyStudentsAdapter);

        MyStudentsAdapter.setOnItemClickListener(new MyStudentsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MyStudentsItem item = MyStudentsList.get(position);

                Bundle extras = new Bundle();
                extras.putString("StudentID", item.getStudentID());
                extras.putString("TutorID", "null");

                Intent i = new Intent(getBaseContext(), DisplayPersonActivity.class);
                i.putExtras(extras);
                startActivity(i);
                finish();


            }

            @Override
            public void onRemoveStudentClick(int position) {
                MyStudentsItem item = MyStudentsList.get(position);

                String removeSQL = "delete from IsTutoring where tutorID=" + TutorID + " and studentID=" + item.getStudentID() + " and skillID=" + item.getSkillID() + ";";

                DBOperator.getInstance().execSQL(removeSQL);

                MyStudentsList.remove(position);

                MyStudentsAdapter.notifyItemRemoved(position);
            }

            @Override
            public void onGraduateStudentClick(int position) {
                MyStudentsItem item = MyStudentsList.get(position);

                String updateSQL = "update IsTutoring set StudentHasPassed=1 where tutorID=" + TutorID + " and studentID=" + item.getStudentID() + " and skillID=" + item.getSkillID() + ";";

                MyStudentsList.remove(position);

                MyStudentsAdapter.notifyItemRemoved(position);

                DBOperator.getInstance().execSQL(updateSQL);



            }
        });
    }

}
