package com.zman2245.pinpin;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.zman2245.pinpin.adapter.grid.AdapterGridReference;

/**
 * Quiz Activity
 *
 * @author zack
 */
public class ActivityReference extends SherlockFragmentActivity
{
    AdapterGridReference mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_main_reference);

        GridView gridView = (GridView)findViewById(R.id.grid);

        String[][] test = {{"row0_col0", "row0_col1", "row0_col2"}, {"row1_col0", "row1_col1", "row1_col2"}};
        mAdapter        = new AdapterGridReference(test, getLayoutInflater());

        gridView.setAdapter(mAdapter);
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
                navigateToPractice(position);

                return true;
            }
        });
    }

    private void navigateToPractice(int index)
    {
        // TBD
//        DataItemLearnFlow[] datas = UtilContentStrings.getLearnSectionData(index);
//
//        FragmentLearnFlow frag  = FragmentLearnFlow.newInstance(datas);
//        FragmentManager fm      = getSupportFragmentManager();
//        FragmentTransaction ft  = fm.beginTransaction();
//
//        ft.add(R.id.container, frag, "learn_flow");
//        ft.addToBackStack("learn_flow");
//        ft.commit();
    }
}
