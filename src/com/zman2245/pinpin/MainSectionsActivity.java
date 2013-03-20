package com.zman2245.pinpin;

import java.io.InputStream;
import java.util.HashMap;

import com.zman2245.pinpin.xml.XmlParserSounds;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.inputmethod.InputBinding;

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

        Intent intent = new Intent(this, ActivityReference.class);
        startActivity(intent);

        // if I go with one base activity with mad fragments
//        Fragment frag               = FragmentMainLearn.newInstance();
//        FragmentManager manager     = getSupportFragmentManager();
//        FragmentTransaction tran    = manager.beginTransaction();
//        tran.add(R.id.container, frag, "frag_learn");
//        tran.commit();
    }
}
