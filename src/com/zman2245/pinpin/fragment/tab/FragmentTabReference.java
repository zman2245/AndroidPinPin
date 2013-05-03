package com.zman2245.pinpin.fragment.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.zman2245.pinpin.AppPinPin;
import com.zman2245.pinpin.R;
import com.zman2245.pinpin.adapter.grid.AdapterGridReference;
import com.zman2245.pinpin.fragment.PinBaseFragment;
import com.zman2245.pinpin.util.audio.Tone;
import com.zman2245.pinpin.util.audio.UtilAudioPlayer;
import com.zman2245.pinpin.util.content.UtilContentStrings;
import com.zman2245.pinpin.util.content.UtilUi;

public class FragmentTabReference extends PinBaseFragment
{
    AdapterGridReference mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_main_reference, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.grid);

        String[][] content = UtilContentStrings.getReferenceStrings(Tone.THIRD);
        mAdapter = new AdapterGridReference(content, inflater);

        gridView.setNumColumns(content[0].length);
        //        gridView.setNumRows(content.length);
        UtilUi.fixGridViewWidth(gridView, content[0].length, getResources().getDimensionPixelSize(R.dimen.gridview_words_cell_width));
        gridView.setAdapter(mAdapter);
        gridView.setOnItemClickListener(new GridView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String word = (String) mAdapter.getItem(position);

                int resId = AppPinPin.getAudioMapper().getResourceForString(word);

                UtilAudioPlayer.playSound(resId);
            }
        });
        gridView.setOnItemLongClickListener(new GridView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                navigateToPractice(position);

                return true;
            }
        });

        return rootView;
    }

    // TODO
    // @Override
    // public boolean onCreateOptionsMenu(Menu menu)
    // {
    // MenuInflater inflater = getSupportMenuInflater();
    //
    // inflater.inflate(R.menu.menu_default, menu);
    //
    // return true;
    // }

    private void setContent(Tone tone)
    {

    }

    private void navigateToPractice(int index)
    {
        // TBD
        // DataItemLearnFlow[] datas =
        // UtilContentStrings.getLearnSectionData(index);
        //
        // FragmentLearnFlow frag = FragmentLearnFlow.newInstance(datas);
        // FragmentManager fm = getSupportFragmentManager();
        // FragmentTransaction ft = fm.beginTransaction();
        //
        // ft.add(R.id.container, frag, "learn_flow");
        // ft.addToBackStack("learn_flow");
        // ft.commit();
    }
}
