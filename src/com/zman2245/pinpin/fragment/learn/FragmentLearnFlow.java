package com.zman2245.pinpin.fragment.learn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zman2245.pinpin.R;
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
    public static final String KEY_TITLE    = "title";
    public static final String KEY_TOPS     = "tops";
    public static final String KEY_BOTTOMS  = "bottoms";

    /**
     * FragmentLearnFlow construction
     *
     * @param title  The main title of the learn flow
     * @param topBodies  The top text sections of the learn flow
     * @param bottomBodies  The bottom text sections of the learn flow
     * @return A new instance of FragmentLearnFlow
     */
    public static FragmentLearnFlow newInstance(DataItemLearnFlow[] data)
    {
        FragmentLearnFlow frag  = new FragmentLearnFlow();
        Bundle args             = new Bundle();

        args.putSerializable("data", data);

        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

//        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_learn_flow, container, false);

        ViewPager pager                 = (ViewPager)rootView.findViewById(R.id.pager);
        PageControl pageControl         = (PageControl)rootView.findViewById(R.id.page_control);

        // just for a test
        DataItemLearnFlow[] datas = new DataItemLearnFlow[2];
        datas[0] = new DataItemLearnFlow();
        datas[1] = new DataItemLearnFlow();
        datas[0].topText = "This is a test for top text of page 0";
        datas[0].bottomText = "This is a test for bottom text of page 0";
        datas[1].topText = "This is a test for top text of page 1";
        datas[1].bottomText = "This is a test for bottom text of page 1";

        PagerAdapterLearnFlow adapter = new PagerAdapterLearnFlow(getChildFragmentManager(), datas);

        pager.setAdapter(adapter);

        return rootView;
    }
}
