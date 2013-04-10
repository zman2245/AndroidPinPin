package com.zman2245.pinpin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.zman2245.pinpin.fragment.PinBaseFragment;
import com.zman2245.pinpin.fragment.tab.FragmentTabLearn;
import com.zman2245.pinpin.fragment.tab.FragmentTabQuiz;
import com.zman2245.pinpin.fragment.tab.FragmentTabReference;
import com.zman2245.pinpin.fragment.tab.TabListener;

/**
 * "Top-level" sections including Learn, Quiz, and Reference Fragments
 * 
 * @author zack
 */
public class MainSectionsActivity extends SherlockFragmentActivity
{
    // store the active tab here
    public static String ACTIVE_TAB = "activeTab";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Tab tab1 = actionBar.newTab().setText(R.string.menu_global_nav_quiz)
                .setTabListener(new TabListener<FragmentTabQuiz>(this, "maintab", FragmentTabQuiz.class));

        actionBar.addTab(tab1, true);

        Tab tab2 = actionBar.newTab().setText(R.string.menu_global_nav_learn)
                .setTabListener(new TabListener<FragmentTabLearn>(this, "maintab", FragmentTabLearn.class));

        actionBar.addTab(tab2);

        Tab tab3 = actionBar.newTab().setText(R.string.menu_global_nav_reference)
                .setTabListener(new TabListener<FragmentTabReference>(this, "maintab", FragmentTabReference.class));

        actionBar.addTab(tab3);

        // check if there is a saved state to select active tab
        if (savedInstanceState != null)
            getSupportActionBar().setSelectedNavigationItem(savedInstanceState.getInt(ACTIVE_TAB));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        // save active tab
        outState.putInt(ACTIVE_TAB, getSupportActionBar().getSelectedNavigationIndex());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed()
    {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentByTag("maintab");

        if (frag != null && frag instanceof PinBaseFragment)
        {
            if (((PinBaseFragment) frag).onBackPressed())
                return;
        }

        super.onBackPressed();
    }
}
