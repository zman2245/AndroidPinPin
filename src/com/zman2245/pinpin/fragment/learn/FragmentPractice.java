package com.zman2245.pinpin.fragment.learn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zman2245.pinpin.R;
import com.zman2245.pinpin.data.DataItemPractice;

/**
 * A fragment for a practice word
 *
 * @author zack
 */
public class FragmentPractice extends Fragment
{
    private static final String KEY_DATA = "data";

    /**
     * FragmentPractice construction
     *
     * @param data  The data this fragment will present
     * @return A new instance of FragmentPractice
     */
    public static FragmentPractice newInstance(DataItemPractice data)
    {
        FragmentPractice frag  = new FragmentPractice();
        Bundle args            = new Bundle();

        args.putSerializable(KEY_DATA, data);
        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_practice, container, false);

        return rootView;
    }
}
