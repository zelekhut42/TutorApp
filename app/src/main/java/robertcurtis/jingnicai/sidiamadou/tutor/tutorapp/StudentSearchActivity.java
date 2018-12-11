package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.DBOperator;
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.StudentSearchAdapter;
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.idObject;

public class StudentSearchActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView StudentSearchRecyclerView;
    private StudentSearchAdapter StudentSearchAdapter;
    private RecyclerView.LayoutManager StudentSearchLayoutManager;
    private ArrayList<StudentSearchItem> StudentSearchList = new ArrayList<>();
    private String enteredSearch;

    private String StudentID;
    private String TutorID;

    Button searchBtn;
    EditText searchEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search);
        searchBtn = findViewById(R.id.StudentSkillSearchButton);
        searchBtn.setOnClickListener(this);
        searchEditText = findViewById(R.id.StudentSearchEditText);



        Intent i = getIntent();
        Bundle extras = i.getExtras();

        StudentID = extras.getString("StudentID");
        TutorID = extras.getString("TutorID");


        StudentSearchRecyclerView = findViewById(R.id.StudentSearchRecyclerView);
        StudentSearchRecyclerView.setHasFixedSize(true);
        StudentSearchLayoutManager = new LinearLayoutManager(this);
        StudentSearchRecyclerView.setLayoutManager(StudentSearchLayoutManager);
        //TutorSearchRecyclerView.setItemAnimator(new DefaultItemAnimator());

        StudentSearchAdapter = new StudentSearchAdapter(StudentSearchList);


        StudentSearchRecyclerView.setAdapter(StudentSearchAdapter);

        StudentSearchAdapter.setOnItemClickListener(new StudentSearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                StudentSearchItem item = StudentSearchList.get(position);

                Bundle extras = new Bundle();
                extras.putString("StudentID", item.getStudentID());
                extras.putString("TutorID", "null");

                Intent i = new Intent(getBaseContext(), DisplayPersonActivity.class);
                i.putExtras(extras);
                startActivity(i);
                finish();


            }

            @Override
            public void onAddStudentClick(int position) {
                StudentSearchItem item = StudentSearchList.get(position);

                if (item.getButtonType().equals("add")) {

                    String[] args = new String[4];



                    args[0] = TutorID;

                    String student = item.getStudentID();

                    args[1] = student;

                    String skillSQL = "select skillID from SkillSet where skill_Name=" + "'" + enteredSearch + "'";

                    DBOperator op = DBOperator.getInstance();

                    Cursor cSkillID = op.execQuery(skillSQL);
                    String skillID = "";

                    while(cSkillID.moveToNext()) {
                        skillID = cSkillID.getString(0);
                    }

                    args[2] = skillID;

                    args[3] = "0";

                    String sql = "insert into IsTutoring(tutorID, studentID, skillID, StudentHasPassed) values(" + TutorID + ", " + item.getStudentID() + ", " + skillID + ", 0)";

                    DBOperator.getInstance().execSQL(sql);

                    String removeSQL = "delete from WantSkill where studentID=" + item.getStudentID() + " and skillID=" + skillID;

                    DBOperator.getInstance().execSQL(removeSQL);

                    StudentSearchList.remove(position);

                    StudentSearchAdapter.notifyItemRemoved(position);




                } else if (item.getButtonType().equals("remove")) {
                    String skillSQL = "select skillID from SkillSet where skill_Name=" + "'" + enteredSearch + "'";

                    DBOperator op = DBOperator.getInstance();

                    Cursor cSkillID = op.execQuery(skillSQL);

                    String skillID = "";

                    while(cSkillID.moveToNext()) {
                        skillID = cSkillID.getString(0);
                    }

                    String removeSQL = "delete from IsTutoring where tutorID=" + TutorID + " and studentID=" + item.getStudentID() + "and skillID=" + skillID + ";";

                    DBOperator.getInstance().execSQL(removeSQL);

                    StudentSearchList.remove(position);

                    StudentSearchAdapter.notifyItemRemoved(position);



                }


            }
        });




    }


    public void onClick(View v)
    {
        int id=v.getId();
        if(id==R.id.StudentSkillSearchButton) {



            StudentSearchList.clear();



            enteredSearch = searchEditText.getText().toString();

            String myStudentsSQL = "select Student.studentID, IsTutoring.StudentHasPassed, IsTutoring.skillID from Student inner join IsTutoring on Student.studentID=IsTutoring.studentID where IsTutoring.tutorID=" + TutorID;


            String sql = "select Student.student_FName, Student.student_LName, Major.major_Name, Student.studentID, WantSkill.skillID from Student inner join WantSkill on Student.studentID=WantSkill.studentID inner join SkillSet on WantSkill.skillID=SkillSet.skillID left join Major on Student.majorID=Major.majorID where SkillSet.skill_Name=" + "'" + enteredSearch + "'";

            DBOperator op = DBOperator.getInstance();

            Cursor cMyStudents = op.execQuery(myStudentsSQL);

            ArrayList<String> MyStudents = new ArrayList<>();
            ArrayList<String> MyStudentsPassed = new ArrayList<>();
            ArrayList<String> MyStudentsSkillID = new ArrayList<>();
            ArrayList<String> MyStudentsPassedSkillID = new ArrayList<>();

            while(cMyStudents.moveToNext()) {
                String isGraduated = cMyStudents.getString(1);

                if (isGraduated.equals("0")) {
                    MyStudents.add(cMyStudents.getString(0));
                    MyStudentsSkillID.add(cMyStudents.getString(2));
                } else if (isGraduated.equals("1")) {
                    MyStudentsPassed.add(cMyStudents.getString(0));
                    MyStudentsPassedSkillID.add(cMyStudents.getString(2));
                }
            }



            Cursor cursor = op.execQuery(sql);


            while(cursor.moveToNext()) {
                String skillID = cursor.getString(4);
                String ID = cursor.getString(3);
                String ButtonType = "";

                Boolean doNotAdd = false;

                if (MyStudents.contains(ID)) {

                    int index = MyStudents.indexOf(ID);

                    if (MyStudentsSkillID.get(index).equals(skillID)) {
                        ButtonType = "remove";
                    } else {
                        ButtonType = "add";
                    }

                }else if (MyStudentsPassed.contains(ID)) {
                    int index = MyStudentsPassed.indexOf(ID);

                    if(MyStudentsPassedSkillID.get(index).equals(skillID)) {
                        doNotAdd = true;
                    } else {
                        ButtonType = "add";
                    }

                } else {
                    ButtonType = "add";
                }

                if (doNotAdd) {
                    //do not add
                } else {
                    StudentSearchList.add(new StudentSearchItem(cursor.getString(0) + " " + cursor.getString(1), cursor.getString(2), ID, ButtonType, skillID));
                }
            }




            StudentSearchAdapter.notifyDataSetChanged();










        }
    }


}
