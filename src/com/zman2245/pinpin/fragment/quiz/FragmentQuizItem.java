package com.zman2245.pinpin.fragment.quiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zman2245.pinpin.R;
import com.zman2245.pinpin.data.DataQuizItem;

/**
 * A fragment for a single quiz question
 *
 * @author zack
 */
public class FragmentQuizItem extends Fragment
{
    private static final String KEY_DATA = "data";

    /**
     * FragmentQuizItem construction
     *
     * @param data  The data this fragment will present
     * @return A new instance of FragmentLearnFlowItem
     */
    public static FragmentQuizItem newInstance(DataQuizItem data)
    {
        FragmentQuizItem frag  = new FragmentQuizItem();
        Bundle args            = new Bundle();

        args.putSerializable(KEY_DATA, data);
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
        View rootView = inflater.inflate(R.layout.fragment_learn_flow_item, container, false);

        return rootView;
    }
}
