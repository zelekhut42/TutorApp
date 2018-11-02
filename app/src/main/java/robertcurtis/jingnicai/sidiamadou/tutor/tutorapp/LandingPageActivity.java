package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class LandingPageActivity extends AppCompatActivity implements OnClickListener {
    private Bundle extras;

    Button SkillPageBtn, TutorSearchBtn, StudentSearchBtn, MyStudentsBtn, MyTutorsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        SkillPageBtn = this.findViewById(R.id.SkillPageButton);
        TutorSearchBtn = this.findViewById(R.id.TutorSkillSearchButton);
        StudentSearchBtn = this.findViewById(R.id.StudentSearchButton);
        MyStudentsBtn = this.findViewById(R.id.MyStudentsButton);
        MyTutorsBtn = this.findViewById(R.id.MyTutorsButton);

        SkillPageBtn.setOnClickListener(this);
        TutorSearchBtn.setOnClickListener(this);
        StudentSearchBtn.setOnClickListener(this);
        MyStudentsBtn.setOnClickListener(this);
        MyTutorsBtn.setOnClickListener(this);


        Intent i = getIntent();
        extras = i.getExtras();

    }

    public void onClick(View v)
    {
        int id=v.getId();
        if(id==R.id.SkillPageButton) {
                Intent i = new Intent(this, SkillPageActivity.class);
                i.putExtras(extras);
                this.startActivity(i);
                finish();
        } else if (id == R.id.TutorSkillSearchButton) {
            if (!"null".equals(extras.getString("StudentID"))) {
                Intent i = new Intent(this, TutorSearchActivity.class);
                i.putExtras(extras);
                this.startActivity(i);
                finish();
            }
        } else if (id == R.id.StudentSearchButton) {
            if (!"null".equals(extras.getString("TutorID"))) {
                Intent i = new Intent(this, StudentSearchActivity.class);
                i.putExtras(extras);
                this.startActivity(i);
                finish();
            }
        } else if (id == R.id.MyStudentsButton) {
            if (!"null".equals(extras.getString("TutorID"))) {
                Intent i = new Intent(this, MyStudentsActivity.class);
                i.putExtras(extras);
                this.startActivity(i);
                finish();
            }
        } else if (id == R.id.MyTutorsButton) {
            if (!"null".equals(extras.getString("StudentID"))) {
                Intent i = new Intent(this, MyTutorsActivity.class);
                i.putExtras(extras);
                this.startActivity(i);
                finish();
            }
        }
    }
}
