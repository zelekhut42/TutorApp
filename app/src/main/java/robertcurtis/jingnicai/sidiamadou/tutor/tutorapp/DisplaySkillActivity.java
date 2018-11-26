package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.DBOperator;

public class DisplaySkillActivity extends AppCompatActivity {
    private Bundle extras;
    private TextView SkillName, SkillDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_skill);
        SkillName = findViewById(R.id.SkillNameValueView);
        SkillDescription = findViewById(R.id.SkillDescriptionValueView);

        Intent i = getIntent();
        extras = i.getExtras();

        String sql = "select SkillSet.skill_Name, SkillSet.s_Description From SkillSet where SkillSet.skillID=" + extras.getString("SkillID");

        DBOperator op = DBOperator.getInstance();



        Cursor cursor = op.execQuery(sql);


        while(cursor.moveToNext()) {
           SkillName.setText(cursor.getString(0));
           SkillDescription.setText(cursor.getString(1));
        }
    }
}
