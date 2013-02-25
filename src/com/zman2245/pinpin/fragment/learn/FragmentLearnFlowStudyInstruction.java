package com.zman2245.pinpin.fragment.learn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zman2245.pinpin.R;

/**
 * A fragment for the "Study this section" page
 *
 * @author zack
 */
public class FragmentLearnFlowStudyInstruction extends Fragment
{
    private static final String KEY_DATA = "data";

    /**
     * FragmentLearnFlowStudyInstruction construction
     *
     * @return A new instance of FragmentLearnFlowStudyInstruction
     */
    public static FragmentLearnFlowStudyInstruction newInstance()
    {
        FragmentLearnFlowStudyInstruction frag  = new FragmentLearnFlowStudyInstruction();

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
        View rootView = inflater.inflate(R.layout.fragment_learn_flow_item, container, false);

        return rootView;
    }
}
