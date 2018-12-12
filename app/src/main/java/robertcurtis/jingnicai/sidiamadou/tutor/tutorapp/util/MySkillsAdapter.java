package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.MySkillsItem;
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.R;

public class MySkillsAdapter extends RecyclerView.Adapter<MySkillsAdapter.MySkillsViewHolder> {
    private ArrayList<MySkillsItem> MySkillsList;
    private OnItemClickListener MySkillsListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onRemoveSkillClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        MySkillsListener = listener;
    }

    public static class MySkillsViewHolder extends RecyclerView.ViewHolder {
        public TextView SkillNameView;
        public ImageView RemoveHaveButton;

        public MySkillsViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            SkillNameView = itemView.findViewById(R.id.MySkillsTextView);
            RemoveHaveButton = itemView.findViewById(R.id.rermoveHaveSkill);

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

            RemoveHaveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onRemoveSkillClick(position);
                        }

                    }

                }
            });

        }
    }

    public MySkillsAdapter(ArrayList<MySkillsItem> list) {
        MySkillsList = list;
    }

    @NonNull
    @Override
    public MySkillsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_skills_item, viewGroup, false);
        MySkillsViewHolder msvh = new MySkillsViewHolder(v, MySkillsListener);
        return msvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MySkillsViewHolder mySkillsViewHolder, int i) {
        MySkillsItem currentItem = MySkillsList.get(i);

        if (currentItem.getHaveButtonType().equals("remove")) {
            mySkillsViewHolder.RemoveHaveButton.setImageResource(R.drawable.ic_remove_box);
        }

        mySkillsViewHolder.SkillNameView.setText(currentItem.getSkillName());
    }

    @Override
    public int getItemCount() {
        return MySkillsList.size();
    }





}
