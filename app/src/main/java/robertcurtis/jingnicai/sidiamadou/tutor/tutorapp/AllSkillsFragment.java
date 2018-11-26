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
    private static final String TAG = "WantedSkillsFragment";

    private RecyclerView AllSkillsRecyclerView;
    private AllSkillsAdapter AllSkillsAdapter;
    private RecyclerView.LayoutManager AllSkillsLayoutManager;
    private ArrayList<AllSkillsItem> AllSkillList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        String sql;


        sql = "select SkillSet.skill_Name, SkillSet.skillID From SkillSet" ;


        Cursor cursor = DBOperator.getInstance().execQuery(sql);

        while(cursor.moveToNext()) {
            AllSkillList.add(new AllSkillsItem(cursor.getString(0), cursor.getString(1)));
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
        });

        return view;
    }
}
