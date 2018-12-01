package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;



public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //Remove title bar
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//
//        //Remove notification bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        //set content view AFTER ABOVE sequence (to avoid crash)
//        this.setContentView(R.layout.activity_welcome);


        setContentView(R.layout.activity_welcome);
        login_button = this.findViewById(R.id.go_to_login_button);
        login_button.setOnClickListener(this);

    }


    public void onClick(View v) {

        Intent i = new Intent(this, TutorActivity.class);
        this.startActivity(i);
        finish();

    }


}
