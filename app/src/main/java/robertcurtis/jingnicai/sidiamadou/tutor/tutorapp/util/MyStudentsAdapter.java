package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.MyStudentsItem;
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.R;

public class MyStudentsAdapter extends RecyclerView.Adapter<MyStudentsAdapter.MyStudentsViewHolder> {

    private ArrayList<MyStudentsItem> MyStudentsList;
    private OnItemClickListener MyStudentsListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        MyStudentsListener = listener;
    }

    public static class MyStudentsViewHolder extends RecyclerView.ViewHolder {
        public TextView StudentNameView, StudentMajorView;

        public MyStudentsViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            StudentNameView = itemView.findViewById(R.id.MyStudentsNameTextView);
            StudentMajorView = itemView.findViewById(R.id.MyStudentsMajorNameTextView);

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

    public MyStudentsAdapter(ArrayList<MyStudentsItem> list) {
        MyStudentsList = list;
    }

    @NonNull
    @Override
    public MyStudentsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_students_item, viewGroup, false);
        MyStudentsViewHolder msvh = new MyStudentsViewHolder(v, MyStudentsListener);
        return msvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyStudentsViewHolder MyStudentsViewHolder, int i) {
        MyStudentsItem currentItem = MyStudentsList.get(i);

        MyStudentsViewHolder.StudentNameView.setText(currentItem.getStudentName());
        String testText = currentItem.getMajorName();

        if (testText == null) {
            testText = "No Major Provided";
        }

        MyStudentsViewHolder.StudentMajorView.setText(testText);
    }

    @Override
    public int getItemCount() {
        return MyStudentsList.size();
    }

}
