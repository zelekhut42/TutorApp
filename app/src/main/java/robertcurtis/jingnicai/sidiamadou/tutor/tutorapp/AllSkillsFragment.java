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
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.AllSkillsAdapter;

public class AllSkillsFragment extends Fragment {
    private static final String TAG = "AllSkillsFragment";

    private RecyclerView AllSkillsRecyclerView;
    private AllSkillsAdapter AllSkillsAdapter;
    private RecyclerView.LayoutManager AllSkillsLayoutManager;
    private ArrayList<AllSkillsItem> AllSkillList = new ArrayList<>();
    private String StudentID;
    private String TutorID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        AllSkillList.clear();

        ArrayList<String> wantedSkills = new ArrayList<>();

        if (!"null".equals(StudentID)) {
            String wantedSkillsSQL = "select SkillSet.skillID From SkillSet inner join WantSkill on SkillSet.skillID=WantSkill.skillID where WantSkill.studentID=" + StudentID;

            Cursor wantedC = DBOperator.getInstance().execQuery(wantedSkillsSQL);

            while (wantedC.moveToNext()) {
                wantedSkills.add(wantedC.getString(0));
            }
        }

        ArrayList<String> mySkills = new ArrayList<>();

        if (!"null".equals(TutorID)) {
            String mySkillsSQL = "select SkillSet.skillID From SkillSet inner join HasSkills on SkillSet.skillID=HasSkills.skillID where HasSkills.tutorID=" + TutorID;

            Cursor myC = DBOperator.getInstance().execQuery(mySkillsSQL);

            while (myC.moveToNext()) {
                mySkills.add(myC.getString(0));
            }
        }


        String sql;


        sql = "select SkillSet.skill_Name, SkillSet.skillID From SkillSet" ;


        Cursor cursor = DBOperator.getInstance().execQuery(sql);

        while(cursor.moveToNext()) {
            String skillID = cursor.getString(1);
            String MySkillsButtonType = "none";
            String WantedSkillsButtonType = "none";

            if (!TutorID.equals("null")) {

                if(mySkills.contains(skillID)) {
                    MySkillsButtonType = "remove";
                } else {
                    MySkillsButtonType = "add";
                }

            }

            if (!StudentID.equals("null")) {

                if(wantedSkills.contains(skillID)) {
                    WantedSkillsButtonType = "remove";
                } else {
                    WantedSkillsButtonType = "add";
                }

            }



            AllSkillList.add(new AllSkillsItem(cursor.getString(0), skillID, WantedSkillsButtonType, MySkillsButtonType));
        }

        View view = inflater.inflate(R.layout.all_skills_fragment, container, false);

        AllSkillsRecyclerView = view.findViewById(R.id.AllSkillsRecyclerView);
        AllSkillsRecyclerView.setHasFixedSize(true);
        AllSkillsLayoutManager = new LinearLayoutManager(getActivity());
        AllSkillsAdapter = new AllSkillsAdapter(AllSkillList);

        AllSkillsRecyclerView.setLayoutManager(AllSkillsLayoutManager);
        AllSkillsRecyclerView.setAdapter(AllSkillsAdapter);

        AllSkillsAdapter.setOnItemClickListener(new AllSkillsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
               AllSkillsItem item = AllSkillList.get(position);

                Bundle extras = new Bundle();
                extras.putString("SkillID", item.getSkillID());


                Intent i = new Intent(getActivity(), DisplaySkillActivity.class);

                i.putExtras(extras);
                startActivity(i);



            }

            @Override
            public void onHaveSkillClick(int position) {
                AllSkillsItem item = AllSkillList.get(position);
                String currentButtonType = item.getHaveButtonType();

                if (currentButtonType.equals("add")) {

                    String sql = "insert into HasSkills values(" + TutorID + "," + item.getSkillID() + ")";

                    DBOperator.getInstance().execSQL(sql);

                    item.setHaveButtonType("remove");

                    AllSkillsAdapter.notifyItemChanged(position);

                } else if (currentButtonType.equals("remove")) {

                    String sql = "delete from HasSkills where tutorID=" + TutorID + " and skillID=" + item.getSkillID() + ";";

                    DBOperator.getInstance().execSQL(sql);

                    item.setHaveButtonType("add");

                    AllSkillsAdapter.notifyItemChanged(position);

                } else if (currentButtonType.equals("none")) {
                    onItemClick(position);
                }

            }

            @Override
            public void onWantSkillClick(int position) {
                AllSkillsItem item = AllSkillList.get(position);
                String currentButtonType = item.getWantButonType();

                if (currentButtonType.equals("add")) {

                    String sql = "insert into WantSkill values(" + StudentID + "," + item.getSkillID() + ")";

                    DBOperator.getInstance().execSQL(sql);

                    item.setWantButonType("remove");

                    AllSkillsAdapter.notifyItemChanged(position);

                } else if (currentButtonType.equals("remove")) {

                    String sql = "delete from WantSkill where studentID=" + StudentID + " and skillID=" + item.getSkillID() + ";";

                    DBOperator.getInstance().execSQL(sql);

                    item.setWantButonType("add");

                    AllSkillsAdapter.notifyItemChanged(position);

                } else if (currentButtonType.equals("none")) {
                    onItemClick(position);
                }
            }
        });



        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        if (getView() != null) {
           AllSkillList.clear();



            AllSkillList.clear();

            ArrayList<String> wantedSkills = new ArrayList<>();

            if (!"null".equals(StudentID)) {
                String wantedSkillsSQL = "select SkillSet.skillID From SkillSet inner join WantSkill on SkillSet.skillID=WantSkill.skillID where WantSkill.studentID=" + StudentID;

                Cursor wantedC = DBOperator.getInstance().execQuery(wantedSkillsSQL);

                while (wantedC.moveToNext()) {
                    wantedSkills.add(wantedC.getString(0));
                }
            }

            ArrayList<String> mySkills = new ArrayList<>();

            if (!"null".equals(TutorID)) {
                String mySkillsSQL = "select SkillSet.skillID From SkillSet inner join HasSkills on SkillSet.skillID=HasSkills.skillID where HasSkills.tutorID=" + TutorID;

                Cursor myC = DBOperator.getInstance().execQuery(mySkillsSQL);

                while (myC.moveToNext()) {
                    mySkills.add(myC.getString(0));
                }
            }


            String sql;


            sql = "select SkillSet.skill_Name, SkillSet.skillID From SkillSet" ;


            Cursor cursor = DBOperator.getInstance().execQuery(sql);

            while(cursor.moveToNext()) {
                String skillID = cursor.getString(1);
                String MySkillsButtonType = "none";
                String WantedSkillsButtonType = "none";

                if (!TutorID.equals("null")) {

                    if(mySkills.contains(skillID)) {
                        MySkillsButtonType = "remove";
                    } else {
                        MySkillsButtonType = "add";
                    }

                }

                if (!StudentID.equals("null")) {

                    if(wantedSkills.contains(skillID)) {
                        WantedSkillsButtonType = "remove";
                    } else {
                        WantedSkillsButtonType = "add";
                    }

                }



                AllSkillList.add(new AllSkillsItem(cursor.getString(0), skillID, WantedSkillsButtonType, MySkillsButtonType));
            }

            AllSkillsAdapter.notifyDataSetChanged();

        }

        super.setUserVisibleHint(isVisibleToUser);
    }

    public void addIDs(String studentID, String tutorID) {
        StudentID = studentID;
        TutorID = tutorID;
    }
}

