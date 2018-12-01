package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    Button login_button;
    Button signup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
        login_button = this.findViewById(R.id.go_to_login_button);
        login_button.setOnClickListener(this);

        signup_button = this.findViewById(R.id.go_to_sign_up_button);
        signup_button.setOnClickListener(this);

    }


    public void onClick(View v) {
         //todo: check initial state to avoid make choice, send you to the the landing page

        int id = v.getId();
        Intent i;
        if (id == R.id.go_to_login_button) {
            i = new Intent(this, LoginActivity.class);
        } else if (id == R.id.go_to_sign_up_button) {
            i = new Intent(this, SignUpActivity.class);
        } else {
            i = new Intent(this, LoginActivity.class);
        }
        this.startActivity(i);
        finish();

    }


}
