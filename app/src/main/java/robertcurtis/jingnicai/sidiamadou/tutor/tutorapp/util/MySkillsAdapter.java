package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.MySkillsItem;
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.R;

public class MySkillsAdapter extends RecyclerView.Adapter<MySkillsAdapter.MySkillsViewHolder> {
    private ArrayList<MySkillsItem> MySkillsList;

    public static class MySkillsViewHolder extends RecyclerView.ViewHolder {
        public TextView SkillNameView;

        public MySkillsViewHolder(@NonNull View itemView) {
            super(itemView);
            SkillNameView = itemView.findViewById(R.id.MySkillsTextView);

        }
    }

    public MySkillsAdapter(ArrayList<MySkillsItem> list) {
        MySkillsList = list;
    }

    @NonNull
    @Override
    public MySkillsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_skills_item, viewGroup, false);
        MySkillsViewHolder msvh = new MySkillsViewHolder(v);
        return msvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MySkillsViewHolder mySkillsViewHolder, int i) {
        MySkillsItem currentItem = MySkillsList.get(i);

        mySkillsViewHolder.SkillNameView.setText(currentItem.getSkillName());
    }

    @Override
    public int getItemCount() {
        return MySkillsList.size();
    }





}
