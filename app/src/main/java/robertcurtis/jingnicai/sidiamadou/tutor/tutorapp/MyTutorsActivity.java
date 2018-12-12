package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
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
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.MyTutorsAdapter;

public class MyTutorsActivity extends AppCompatActivity {

    private RecyclerView MyTutorsRecyclerView;
    private MyTutorsAdapter MyTutorsAdapter;
    private RecyclerView.LayoutManager MyTutorsLayoutManager;
    private ArrayList<MyTutorsItem> MyTutorsList = new ArrayList<>();

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





        String sql = "select Tutor.tutor_FName, Tutor.tutor_LName, Major.major_Name, Tutor.tutorID, SkillSet.skillID, SkillSet.skill_Name, IsTutoring.StudentHasPassed from Tutor inner join IsTutoring on Tutor.tutorID=IsTutoring.tutorID inner join SkillSet on IsTutoring.skillID=SkillSet.skillID left join Major on Tutor.majorID=Major.majorID where IsTutoring.studentID=" + StudentID + ";";


        DBOperator op = DBOperator.getInstance();



        Cursor cursor = op.execQuery(sql);


        while(cursor.moveToNext()) {
            String hasPassed = cursor.getString(6);

            if(hasPassed.equals("0")) {

                try {
                    MyTutorsList.add(new MyTutorsItem(cursor.getString(0) + " " + cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));
                } catch (Exception e) {
                    MyTutorsList.add(new MyTutorsItem(cursor.getString(0) + " " + cursor.getString(1), "None Listed", cursor.getString(3), cursor.getString(4), cursor.getString(5)));
                }
            } else {
                //do nothing
            }
        }




        MyTutorsRecyclerView = findViewById(R.id.MyTutorsRecyclerView);
        MyTutorsRecyclerView.setHasFixedSize(true);
        MyTutorsLayoutManager = new LinearLayoutManager(this);
        MyTutorsRecyclerView.setLayoutManager(MyTutorsLayoutManager);



        MyTutorsAdapter = new MyTutorsAdapter(MyTutorsList);


        MyTutorsRecyclerView.setAdapter(MyTutorsAdapter);

        MyTutorsAdapter.setOnItemClickListener(new MyTutorsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MyTutorsItem item = MyTutorsList.get(position);

                Bundle extras = new Bundle();
                extras.putString("StudentID", "null");
                extras.putString("TutorID", item.getTutorID());

                Intent i = new Intent(getBaseContext(), DisplayPersonActivity.class);
                i.putExtras(extras);
                startActivity(i);
                finish();


            }

            @Override
            public void onRemoveTutorClick(int position) {
                MyTutorsItem item = MyTutorsList.get(position);

                String removeSQL = "delete from IsTutoring where tutorID=" + item.getTutorID() + " and studentID=" + StudentID + " and skillID=" + item.getSkillID() + ";";

                DBOperator.getInstance().execSQL(removeSQL);

                MyTutorsList.remove(position);

                MyTutorsAdapter.notifyItemRemoved(position);
            }
        });

    }

}


