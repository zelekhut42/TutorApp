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
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.WantedSkillsAdapter;

public class WantedSkillsFragment extends Fragment {
    private static final String TAG = "WantedSkillsFragment";
    private RecyclerView WantedSkillsRecyclerView;
    private WantedSkillsAdapter WantedSkillsAdapter;
    private RecyclerView.LayoutManager WantedSkillsLayoutManager;
    private ArrayList<WantedSkillsItem> WantedSkillList;

    private String StudentID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        WantedSkillList = new ArrayList<>();

        String sql;


        sql = "select SkillSet.skill_Name, SkillSet.skillID From SkillSet inner join WantSkill on SkillSet.skillID=WantSkill.skillID where WantSkill.studentID=" + StudentID;


        Cursor cursor = DBOperator.getInstance().execQuery(sql);

        while (cursor.moveToNext()) {
            WantedSkillList.add(new WantedSkillsItem(cursor.getString(0), cursor.getString(1)));
        }

        View view = inflater.inflate(R.layout.wanted_skills_fragment, container, false);

        WantedSkillsRecyclerView = view.findViewById(R.id.WantedSkillsRecyclerView);
        WantedSkillsRecyclerView.setHasFixedSize(true);
        WantedSkillsLayoutManager = new LinearLayoutManager(getActivity());
        WantedSkillsAdapter = new WantedSkillsAdapter(WantedSkillList);

        WantedSkillsRecyclerView.setLayoutManager(WantedSkillsLayoutManager);
        WantedSkillsRecyclerView.setAdapter(WantedSkillsAdapter);

        WantedSkillsAdapter.setOnItemClickListener(new WantedSkillsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                WantedSkillsItem item = WantedSkillList.get(position);

                Bundle extras = new Bundle();
                extras.putString("SkillID", item.getSkillID());


                Intent i = new Intent(getActivity(), DisplaySkillActivity.class);

                i.putExtras(extras);
                startActivity(i);


            }

            @Override
            public void onRemoveWantedSkillClick(int position) {
                WantedSkillsItem item = WantedSkillList.get(position);

                String sql = "delete from WantSkill where studentID=" + StudentID + " and skillID=" + item.getSkillID() + ";";

                DBOperator.getInstance().execSQL(sql);

                WantedSkillList.remove(position);


                WantedSkillsAdapter.notifyItemRemoved(position);
            }
        });

        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        if (getView() != null) {
            WantedSkillList.clear();

            String sql;


            sql = "select SkillSet.skill_Name, SkillSet.skillID From SkillSet inner join WantSkill on SkillSet.skillID=WantSkill.skillID where WantSkill.studentID=" + StudentID;


            Cursor cursor = DBOperator.getInstance().execQuery(sql);

            while (cursor.moveToNext()) {
                WantedSkillList.add(new WantedSkillsItem(cursor.getString(0), cursor.getString(1)));
            }

            WantedSkillsAdapter.notifyDataSetChanged();

        }


        super.setUserVisibleHint(isVisibleToUser);
    }

    public void addID(String studentID) {
        StudentID = studentID;
    }

}
