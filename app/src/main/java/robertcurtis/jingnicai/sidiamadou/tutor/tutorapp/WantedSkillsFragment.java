package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

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

import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.R;
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.DBOperator;
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.WantedSkillsAdapter;

public class WantedSkillsFragment extends Fragment {
    private static final String TAG = "WantedSkillsFragment";
    private RecyclerView WantedSkillsRecyclerView;
    private RecyclerView.Adapter WantedSkillsAdapter;
    private RecyclerView.LayoutManager WantedSkillsLayoutManager;

    private String StudentID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ArrayList<WantedSkillsItem> WantedSkillList = new ArrayList<>();
        String sql;


        sql = "select SkillSet.skill_Name From SkillSet inner join WantSkill on SkillSet.skillID=WantSkill.skillID where WantSkill.studentID=" + StudentID;


        Cursor cursor = DBOperator.getInstance().execQuery(sql);

        while(cursor.moveToNext()) {
            WantedSkillList.add(new WantedSkillsItem(cursor.getString(0)));
        }

        View view = inflater.inflate(R.layout.wanted_skills_fragment, container, false);

        WantedSkillsRecyclerView = view.findViewById(R.id.WantedSkillsRecyclerView);
        WantedSkillsRecyclerView.setHasFixedSize(true);
        WantedSkillsLayoutManager = new LinearLayoutManager(getActivity());
        WantedSkillsAdapter = new WantedSkillsAdapter(WantedSkillList);

        WantedSkillsRecyclerView.setLayoutManager(WantedSkillsLayoutManager);
        WantedSkillsRecyclerView.setAdapter(WantedSkillsAdapter);

       return view;
    }

    public void addID(String studentID) {
        StudentID = studentID;
    }

}
