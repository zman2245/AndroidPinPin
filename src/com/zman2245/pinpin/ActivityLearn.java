package com.zman2245.pinpin;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.zman2245.pinpin.adapter.list.AdapterListLearn;
import com.zman2245.pinpin.data.DataItemLearnFlow;
import com.zman2245.pinpin.fragment.learn.FragmentLearnFlow;
import com.zman2245.pinpin.util.content.UtilContentStrings;

/**
 * Learn Activity
 * 
 * @author zack
 */
public class ActivityLearn extends FragmentActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_main_learn);

        ListView listView = (ListView) findViewById(R.id.list);
        final AdapterListLearn adapter = new AdapterListLearn(this, getLayoutInflater());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                navigateToLearnSection(position);
            }
        });
    }

    private void navigateToLearnSection(int index)
    {
        DataItemLearnFlow[] datas = UtilContentStrings.getLearnSectionData(index);

        FragmentLearnFlow frag = FragmentLearnFlow.newInstance(datas, index == 0);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.container, frag, "learn_flow");
        ft.addToBackStack("learn_flow");
        ft.commit();
    }
}
