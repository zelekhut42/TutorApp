package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.DBOperator;
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.MySkillsAdapter;

public class MySkillsFragment extends Fragment {
    private static final String TAG = "MySkillsFragment";

    private RecyclerView MySkillsRecyclerView;
    private MySkillsAdapter MySkillsAdapter;
    private RecyclerView.LayoutManager MySkillsLayoutManager;
    private ArrayList<MySkillsItem> MySkillList = new ArrayList<>();

    private String StudentID;
    private String TutorID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MySkillList.clear();



        String sql;

        if(!"null".equals(TutorID)) {
            sql = "select SkillSet.skill_Name, SkillSet.skillID From SkillSet inner join HasSkills on SkillSet.skillID=HasSkills.skillID where HasSkills.tutorID=" + TutorID;
        } else {
            sql = "select SkillSet.skill_Name, SkillSet.skillID From SkillSet inner join IsTutoring on SkillSet.skillID=IsTutoring.skillID where IsTutoring.StudentHasPassed=1 and IsTutoring.studentID=" + StudentID;
        }

        Cursor cursor = DBOperator.getInstance().execQuery(sql);

        while(cursor.moveToNext()) {
            MySkillList.add(new MySkillsItem(cursor.getString(0), cursor.getString(1)));
        }
        View view = inflater.inflate(R.layout.my_skills_fragment, container, false);
        MySkillsRecyclerView = view.findViewById(R.id.MySkillsRecyclerView);
        MySkillsRecyclerView.setHasFixedSize(true);
        MySkillsLayoutManager = new LinearLayoutManager(getActivity());
        MySkillsAdapter = new MySkillsAdapter(MySkillList);

        MySkillsRecyclerView.setLayoutManager(MySkillsLayoutManager);
        MySkillsRecyclerView.setAdapter(MySkillsAdapter);

        MySkillsAdapter.setOnItemClickListener(new MySkillsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MySkillsItem item = MySkillList.get(position);

                Bundle extras = new Bundle();
                extras.putString("SkillID", item.getSkillID());


                Intent i = new Intent(getActivity(), DisplaySkillActivity.class);

                i.putExtras(extras);
                startActivity(i);



            }
        });


        return view;
    }

    public void addIDs(String studentID, String tutorID) {
        StudentID = studentID;
        TutorID = tutorID;
    }

}
