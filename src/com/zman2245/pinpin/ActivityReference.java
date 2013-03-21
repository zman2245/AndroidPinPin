package com.zman2245.pinpin;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.zman2245.pinpin.adapter.grid.AdapterGridReference;
import com.zman2245.pinpin.util.audio.Tone;
import com.zman2245.pinpin.util.audio.UtilAudioPlayer;
import com.zman2245.pinpin.util.content.UtilContentStrings;
import com.zman2245.pinpin.util.content.UtilUi;

/**
 * Reference Activity
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

        String[][] content = UtilContentStrings.getReferenceStrings(Tone.THIRD);
        mAdapter           = new AdapterGridReference(content, getLayoutInflater());

        gridView.setNumColumns(content[0].length);
        UtilUi.fixGridViewWidth(gridView, content[0].length, getResources().getDimensionPixelSize(R.dimen.gridview_words_cell_width));
        gridView.setAdapter(mAdapter);
        gridView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String word = (String)mAdapter.getItem(position);

                int resId = AppPinPin.getAudioMapper().getResourceForString(word);

                UtilAudioPlayer.playSound(resId);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getSupportMenuInflater();

        inflater.inflate(R.menu.menu_default, menu);

        return true;
    }

    private void setContent(Tone tone)
    {

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
