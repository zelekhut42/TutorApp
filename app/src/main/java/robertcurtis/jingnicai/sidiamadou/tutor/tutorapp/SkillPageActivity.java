package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class SkillPageActivity extends AppCompatActivity {
    public static final String TAG = "SkillPageActivity";

    private Bundle extras;

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_page);

        Intent i = getIntent();
        extras = i.getExtras();

        Log.d(TAG, "onCreate: Starting.");

        //mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }


    //extras.getString("StudentID") != "null"
    //extras.getString("TutorID") != "null"
    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());

        MySkillsFragment msfrag = new MySkillsFragment();
        msfrag.addIDs(extras.getString("StudentID"), extras.getString("TutorID"));

        adapter.addFragment(msfrag, "MySkills");

        WantedSkillsFragment wsfrag = new WantedSkillsFragment();
        wsfrag.addID(extras.getString("StudentID"));

        if(!"null".equals(extras.getString("StudentID"))) {

            adapter.addFragment(wsfrag, "WantedSkills");
        }

        AllSkillsFragment asfrag = new AllSkillsFragment();
        asfrag.addIDs(extras.getString("StudentID"), extras.getString("TutorID"));
        adapter.addFragment(asfrag, "AllSkills");



        viewPager.setAdapter(adapter);
    }
}
