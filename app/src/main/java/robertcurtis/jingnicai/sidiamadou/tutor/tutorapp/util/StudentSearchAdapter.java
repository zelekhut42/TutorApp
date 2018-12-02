package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.StudentSearchItem;
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.R;

public class StudentSearchAdapter extends RecyclerView.Adapter<StudentSearchAdapter.StudentSearchViewHolder> {

    private ArrayList<StudentSearchItem> StudentSearchList;
    private OnItemClickListener StudentSearchListener;


    public interface OnItemClickListener {
        void onItemClick(int position);
        void onAddStudentClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        StudentSearchListener = listener;
    }

    public static class StudentSearchViewHolder extends RecyclerView.ViewHolder {
        public TextView StudentNameView, MajorNameView;
        public ImageView addStudent;

        public StudentSearchViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            StudentNameView = itemView.findViewById(R.id.StudentSearchNameTextView);
            MajorNameView = itemView.findViewById(R.id.StudentSearchMajorNameTextView);
            addStudent = itemView.findViewById(R.id.addStudent);

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

            addStudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onAddStudentClick(position);
                        }

                    }

                }
            });

        }
    }

    public StudentSearchAdapter(ArrayList<StudentSearchItem> list) {
        StudentSearchList = list;
    }

    @NonNull
    @Override
    public StudentSearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student_search_item, viewGroup, false);
        StudentSearchViewHolder msvh = new StudentSearchViewHolder(v, StudentSearchListener);
        return msvh;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentSearchViewHolder StudentSearchHolder, int i) {
        StudentSearchItem currentItem = StudentSearchList.get(i);
        if (currentItem.getButtonType().equals("add")) {
            StudentSearchHolder.addStudent.setImageResource(R.drawable.ic_add);
        } else if (currentItem.getButtonType().equals("remove")) {
            StudentSearchHolder.addStudent.setImageResource(R.drawable.ic_remove);
        }



        StudentSearchHolder.StudentNameView.setText(currentItem.getStudentName());
        StudentSearchHolder.MajorNameView.setText(currentItem.getMajorName());
    }

    @Override
    public int getItemCount() {
        return StudentSearchList.size();
    }
}
