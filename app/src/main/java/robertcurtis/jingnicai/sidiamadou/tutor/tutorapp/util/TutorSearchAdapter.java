package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.TutorSearchItem;
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.R;


public class TutorSearchAdapter extends RecyclerView.Adapter<TutorSearchAdapter.TutorSearchViewHolder>{
    private ArrayList<TutorSearchItem> TutorSearchList;
    private OnItemClickListener TutorSearchListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        TutorSearchListener = listener;
    }

    public static class TutorSearchViewHolder extends RecyclerView.ViewHolder {
        public TextView TutorNameView, MajorNameView;

        public TutorSearchViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            TutorNameView = itemView.findViewById(R.id.TutorSearchNameTextView);
            MajorNameView = itemView.findViewById(R.id.TutorSearchMajorNameTextView);

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

    public TutorSearchAdapter(ArrayList<TutorSearchItem> list) {
        TutorSearchList = list;
    }

    @NonNull
    @Override
    public TutorSearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tutor_search_item, viewGroup, false);
        TutorSearchViewHolder msvh = new TutorSearchViewHolder(v, TutorSearchListener);
        return msvh;
    }

    @Override
    public void onBindViewHolder(@NonNull TutorSearchViewHolder TutorSearchHolder, int i) {
        TutorSearchItem currentItem = TutorSearchList.get(i);

        TutorSearchHolder.TutorNameView.setText(currentItem.getTutorName());
        TutorSearchHolder.MajorNameView.setText(currentItem.getMajorName());
    }

    @Override
    public int getItemCount() {
        return TutorSearchList.size();
    }






}
