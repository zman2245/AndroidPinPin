package com.zman2245.pinpin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.zman2245.pinpin.fragment.FragmentMainLearn;

/**
 * "Top-level" sections including Learn, Quiz, and Reference Fragments
 *
 * @author zack
 */
public class MainSectionsActivity extends FragmentActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_sections);

        Fragment frag               = FragmentMainLearn.newInstance();
        FragmentManager manager     = getSupportFragmentManager();
        FragmentTransaction tran    = manager.beginTransaction();
        tran.add(R.id.container, frag, "frag_learn");
        tran.commit();
    }
}
