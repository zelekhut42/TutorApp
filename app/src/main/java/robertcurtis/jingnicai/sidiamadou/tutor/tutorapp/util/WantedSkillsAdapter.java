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
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.WantedSkillsItem;


public class WantedSkillsAdapter extends RecyclerView.Adapter<WantedSkillsAdapter.WantedSkillsViewHolder>{


    private ArrayList<WantedSkillsItem> WantedSkillsList;
    private OnItemClickListener WantedSkillsListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        WantedSkillsListener = listener;
    }

    public static class WantedSkillsViewHolder extends RecyclerView.ViewHolder {
        public TextView SkillNameView;

        public WantedSkillsViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            SkillNameView = itemView.findViewById(R.id.WantedSkillsTextView);

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

    public WantedSkillsAdapter(ArrayList<WantedSkillsItem> list) {
        WantedSkillsList = list;
    }

    @NonNull
    @Override
    public WantedSkillsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wanted_skills_item, viewGroup, false);
        WantedSkillsViewHolder msvh = new WantedSkillsViewHolder(v, WantedSkillsListener);
        return msvh;
    }

    @Override
    public void onBindViewHolder(@NonNull WantedSkillsViewHolder WantedSkillsViewHolder, int i) {
        WantedSkillsItem currentItem = WantedSkillsList.get(i);

        WantedSkillsViewHolder.SkillNameView.setText(currentItem.getSkillName());
    }

    @Override
    public int getItemCount() {
        return WantedSkillsList.size();
    }

















}
