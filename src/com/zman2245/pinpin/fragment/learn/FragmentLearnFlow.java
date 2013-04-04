package com.zman2245.pinpin.fragment.learn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zman2245.pinpin.R;
import com.zman2245.pinpin.adapter.viewpager.PagerAdapterIntroductionLearnFlow;
import com.zman2245.pinpin.adapter.viewpager.PagerAdapterLearnFlow;
import com.zman2245.pinpin.data.DataItemLearnFlow;
import com.zman2245.pinpin.view.pagecontrol.PageControl;

/**
 * A fragment for a learn flow
 * 
 * Contains some flow of views
 * 
 * @author zack
 */
public class FragmentLearnFlow extends Fragment
{
    public static final String KEY_DATAS = "datas";
    public static final String KEY_INTRO = "isIntro";

    /**
     * FragmentLearnFlow construction
     * 
     * @param title
     *            The main title of the learn flow
     * @param topBodies
     *            The top text sections of the learn flow
     * @param bottomBodies
     *            The bottom text sections of the learn flow
     * @return A new instance of FragmentLearnFlow
     */
    public static FragmentLearnFlow newInstance(DataItemLearnFlow[] datas, boolean isIntro)
    {
        FragmentLearnFlow frag = new FragmentLearnFlow();
        Bundle args = new Bundle();

        args.putSerializable(KEY_DATAS, datas);
        args.putBoolean(KEY_INTRO, isIntro);

        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_learn_flow, container, false);

        ViewPager pager = (ViewPager) rootView.findViewById(R.id.pager);
        final PageControl pageControl = (PageControl) rootView.findViewById(R.id.page_control);

        // just for a test
        DataItemLearnFlow[] datas = (DataItemLearnFlow[]) getArguments().getSerializable(KEY_DATAS);

        // the intro flow has a much different format, so it is separated
        FragmentStatePagerAdapter adapter;
        if (getArguments().getBoolean(KEY_INTRO))
            adapter = new PagerAdapterIntroductionLearnFlow(getChildFragmentManager(), datas);
        else
            adapter = new PagerAdapterLearnFlow(getChildFragmentManager(), datas);

        pager.setAdapter(adapter);
        pageControl.setPageNumber(datas.length + 1);
        pageControl.setSelectedPageIndex(0);

        pager.setOnPageChangeListener(new OnPageChangeListener()
        {
            @Override
            public void onPageSelected(int index)
            {
                pageControl.setSelectedPageIndex(index);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2)
            {
            }

            @Override
            public void onPageScrollStateChanged(int arg0)
            {
            }
        });

        return rootView;
    }
}
