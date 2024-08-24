package com.example.itsadeal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class first_page extends AppCompatActivity {
    private LottieAnimationView lottianimationview;
    private FrameLayout container;
    private TextView welcome;
    BottomNavigationView bottombar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        String userEmail = getIntent().getStringExtra("user_email");

        bottombar = findViewById(R.id.bottombar);
        bottombar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment fragment = null;
                if (id == R.id.order) {
                    fragment = new BlankFragment4();
                } else if (id == R.id.our_team) {
                    fragment = new BlankFragment3();
                } else {
                    fragment = new BlankFragment();
                    // Pass the email to the fragment
                    Bundle bundle = new Bundle();
                    bundle.putString("user_email", userEmail);
                    fragment.setArguments(bundle);
                }
                loadfrag(fragment, false);
                return true;
            }
        });
        bottombar.setSelectedItemId(R.id.homa);
    }

    public void loadfrag(Fragment fragment, boolean flag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (flag) {
            ft.add(R.id.container, fragment);
        } else {
            ft.replace(R.id.container, fragment);
        }
        ft.commit();
    }
}
