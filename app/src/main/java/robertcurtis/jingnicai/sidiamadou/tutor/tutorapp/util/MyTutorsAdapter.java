package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.MyTutorsItem;
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.R;


public class MyTutorsAdapter extends RecyclerView.Adapter<MyTutorsAdapter.MyTutorsViewHolder>{

    private ArrayList<MyTutorsItem> MyTutorsList;
    private OnItemClickListener MyTutorListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        MyTutorListener = listener;
    }

    public static class MyTutorsViewHolder extends RecyclerView.ViewHolder {
        public TextView TutorNameView, TutorMajorView;

        public MyTutorsViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            TutorNameView = itemView.findViewById(R.id.MyTutorsNameTextView);
            TutorMajorView = itemView.findViewById(R.id.MyTutorsMajorNameTextView);

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

    public MyTutorsAdapter(ArrayList<MyTutorsItem> list) {
        MyTutorsList = list;
    }

    @NonNull
    @Override
    public MyTutorsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_tutors_item, viewGroup, false);
        MyTutorsViewHolder msvh = new MyTutorsViewHolder(v, MyTutorListener);
        return msvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyTutorsViewHolder MyTutorsViewHolder, int i) {
        MyTutorsItem currentItem = MyTutorsList.get(i);

        MyTutorsViewHolder.TutorNameView.setText(currentItem.getTutorName());
        String testText = currentItem.getMajorName();

        if (testText == null) {
            testText = "No Major Provided";
        }

        MyTutorsViewHolder.TutorMajorView.setText(testText);
    }

    @Override
    public int getItemCount() {
        return MyTutorsList.size();
    }
}
