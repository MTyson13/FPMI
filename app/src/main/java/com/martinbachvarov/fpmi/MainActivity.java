package com.martinbachvarov.fpmi;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import layout.HomeLoginFragment;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openHomePageFragment();
    }

    private void openHomePageFragment() {
        FragmentTransaction fTransaction = getSupportFragmentManager().beginTransaction();
        fTransaction.replace(R.id.container, new HomeLoginFragment());
        fTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}