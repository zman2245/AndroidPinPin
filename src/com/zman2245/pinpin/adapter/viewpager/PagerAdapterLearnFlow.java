package com.zman2245.pinpin.adapter.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zman2245.pinpin.data.DataItemLearnFlow;
import com.zman2245.pinpin.fragment.learn.FragmentLearnFlowItem;

/**
 * A view pager adapter for the Learn flows
 *
 * TODO: add the final "study this section page"  ? decorator?
 *
 * @author zack
 */
public class PagerAdapterLearnFlow extends FragmentStatePagerAdapter
{
    DataItemLearnFlow[] mData;

    public PagerAdapterLearnFlow(FragmentManager fm, DataItemLearnFlow[] data)
    {
        super(fm);

        mData = data;
    }

    @Override
    public Fragment getItem(int index)
    {
        return FragmentLearnFlowItem.newInstance(mData[index]);
    }

    @Override
    public int getCount()
    {
        return mData.length;
    }
}
