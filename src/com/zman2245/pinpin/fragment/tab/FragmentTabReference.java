package com.zman2245.pinpin.fragment.tab;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
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
    private GridView             mGridView;
    private LinearLayout         mTopbar;
    private AdapterGridReference mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_main_reference, container, false);

        setHasOptionsMenu(true);

        mTopbar   = (LinearLayout) rootView.findViewById(R.id.topbar);
        mGridView = (GridView) rootView.findViewById(R.id.grid);

        setContentTone(Tone.FIRST);

        mGridView.setOnItemClickListener(new GridView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String word = (String) mAdapter.getItem(position);

                int resId = AppPinPin.getAudioMapper().getResourceForString(word);

                UtilAudioPlayer.playSound(resId);
            }
        });
        mGridView.setOnItemLongClickListener(new GridView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                navigateToPractice(position);

                return true;
            }
        });

        initTopbar();

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_reference, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
        case R.id.menu_item_tone_first:
            setContentTone(Tone.FIRST);
            return true;

        case R.id.menu_item_tone_second:
            setContentTone(Tone.SECOND);
            return true;

        case R.id.menu_item_tone_third:
            setContentTone(Tone.THIRD);
            return true;

        case R.id.menu_item_tone_fourth:
            setContentTone(Tone.FOURTH);
            return true;

        default:
            return super.onOptionsItemSelected(item);
        }
    }

    private void setContentTone(Tone tone)
    {
        String[][] content  = UtilContentStrings.getReferenceStrings(tone);
        mAdapter            = new AdapterGridReference(content, getLayoutInflater(null));

        UtilUi.fixGridViewWidth(mGridView, content[0].length, getResources().getDimensionPixelSize(R.dimen.gridview_words_cell_width));
        mGridView.setNumColumns(content[0].length);
        mGridView.setAdapter(mAdapter);
    }

    private void initTopbar()
    {
        String[] topbarWords = AppPinPin.getStringArray(R.array.reference_topbar);

        for (String word : topbarWords)
        {
            TextView txtView = new TextView(getActivity());
            txtView.setText(word);
            txtView.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams lps = new LinearLayout.LayoutParams(
                    getResources().getDimensionPixelSize(R.dimen.gridview_words_cell_width),
                    getResources().getDimensionPixelSize(R.dimen.gridview_words_cell_height));
            txtView.setTextSize(getResources().getDimensionPixelSize(R.dimen.textsize_reference_topbar));
            txtView.setLayoutParams(lps);
            mTopbar.addView(txtView);
        }
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
