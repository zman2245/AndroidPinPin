package com.zman2245.pinpin;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.zman2245.pinpin.adapter.list.AdapterListQuiz;
import com.zman2245.pinpin.data.DataItemLearnFlow;
import com.zman2245.pinpin.fragment.learn.FragmentLearnFlow;
import com.zman2245.pinpin.util.UtilContentStrings;

/**
 * Quiz Activity
 *
 * @author zack
 */
public class ActivityQuiz extends SherlockFragmentActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_main_quiz);

        ListView listView               = (ListView)findViewById(R.id.list);
        final AdapterListQuiz adapter   = new AdapterListQuiz(this, getLayoutInflater());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                navigateToQuizSection(position);
            }
        });
    }

    private void navigateToQuizSection(int index)
    {
        // TBD
        DataItemLearnFlow[] datas = UtilContentStrings.getLearnSectionData(index);

        FragmentLearnFlow frag  = FragmentLearnFlow.newInstance(datas);
        FragmentManager fm      = getSupportFragmentManager();
        FragmentTransaction ft  = fm.beginTransaction();

        ft.add(R.id.container, frag, "learn_flow");
        ft.addToBackStack("learn_flow");
        ft.commit();
    }
}
