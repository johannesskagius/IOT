package com.example.node_red_01;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;        //Skapar en fragment manager för att visa olika fragments
    private int switchDeveloper = 2;
    private boolean isSet= false;
    private String place = "hall";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        changeFragment(new HomeFragment(this));
    }

    public void changeFragment(Fragment fragment){
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, null).commit();
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}