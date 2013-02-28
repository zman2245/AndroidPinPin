package com.zman2245.pinpin;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.zman2245.pinpin.adapter.grid.AdapterGridReference;
import com.zman2245.pinpin.data.DataItemLearnFlow;
import com.zman2245.pinpin.fragment.learn.FragmentLearnFlow;
import com.zman2245.pinpin.util.UtilContentStrings;

/**
 * Quiz Activity
 *
 * @author zack
 */
public class ActivityReference extends SherlockFragmentActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_main_reference);

        GridView gridView               = (GridView)findViewById(R.id.grid);
        AdapterGridReference adapter    = new AdapterGridReference();

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // TODO: play some audio
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                return true;
            }
        });
    }

    private void navigateToPractice(int index)
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
