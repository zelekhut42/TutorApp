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

public class MyTutorsActivity extends AppCompatActivity {

    private RecyclerView MyTutorsRecyclerView;
    private RecyclerView.Adapter MyTutorsAdapter;
    private RecyclerView.LayoutManager MyTutorsLayoutManager;

    private String StudentID;
    private String TutorID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tutors);
        Intent i = getIntent();
        Bundle extras = i.getExtras();

        StudentID = extras.getString("StudentID");
        TutorID = extras.getString("TutorID");



        ArrayList<MyStudentsItem> MyTutorsList = new ArrayList<>();

        String sql = "select Tutor.tutor_FName, Tutor.tutor_LName, Major.major_Name from Tutor inner join IsTutoring on Tutor.tutorID=IsTutoring.tutorID left join Major on Tutor.majorID=Major.majorID where IsTutoring.studentID=" + StudentID;


        DBOperator op = DBOperator.getInstance();



        Cursor cursor = op.execQuery(sql);


        while(cursor.moveToNext()) {
            try {
                MyTutorsList.add(new MyStudentsItem(cursor.getString(0) + " " + cursor.getString(1), cursor.getString(2)));
            } catch (Exception e) {
                MyTutorsList.add(new MyStudentsItem(cursor.getString(0) + " " + cursor.getString(1), "None Listed"));
            }
        }




        MyTutorsRecyclerView = findViewById(R.id.MyTutorsRecyclerView);
        MyTutorsRecyclerView.setHasFixedSize(true);
        MyTutorsLayoutManager = new LinearLayoutManager(this);
        MyTutorsRecyclerView.setLayoutManager(MyTutorsLayoutManager);



        MyTutorsAdapter = new MyStudentsAdapter(MyTutorsList);


        MyTutorsRecyclerView.setAdapter(MyTutorsAdapter);

    }

}
