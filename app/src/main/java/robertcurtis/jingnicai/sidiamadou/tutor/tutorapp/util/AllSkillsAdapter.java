package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.AllSkillsItem;
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.R;

public class AllSkillsAdapter extends RecyclerView.Adapter<AllSkillsAdapter.AllSkillsViewHolder>{
    private ArrayList<AllSkillsItem> AllSkillsList;
    private OnItemClickListener AllSkillsListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        AllSkillsListener = listener;
    }

    public static class AllSkillsViewHolder extends RecyclerView.ViewHolder {
        public TextView SkillNameView;

        public AllSkillsViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            SkillNameView = itemView.findViewById(R.id.AllSkillsTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }

                    }

                }
            });

        }
    }

        public AllSkillsAdapter(ArrayList<AllSkillsItem> list) {
            AllSkillsList = list;
        }

        @NonNull
        @Override
        public AllSkillsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.all_skills_item, viewGroup, false);
            AllSkillsViewHolder msvh = new AllSkillsViewHolder(v, AllSkillsListener);
            return msvh;
        }

        @Override
        public void onBindViewHolder(@NonNull AllSkillsViewHolder AllSkillsViewHolder, int i) {
            AllSkillsItem currentItem = AllSkillsList.get(i);

            AllSkillsViewHolder.SkillNameView.setText(currentItem.getSkillName());
        }

        @Override
        public int getItemCount() {
            return AllSkillsList.size();
        }

}
