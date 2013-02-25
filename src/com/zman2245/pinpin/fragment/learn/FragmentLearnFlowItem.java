package com.zman2245.pinpin.fragment.learn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zman2245.pinpin.R;
import com.zman2245.pinpin.data.DataItemLearnFlow;

/**
 * A fragment for a single learn flow item (aka page)
 *
 * @author zack
 */
public class FragmentLearnFlowItem extends Fragment
{
    private static final String KEY_DATA = "data";

    /**
     * FragmentLearnFlowItem construction
     *
     * @param data  The data this fragment will present
     * @return A new instance of FragmentLearnFlowItem
     */
    public static FragmentLearnFlowItem newInstance(DataItemLearnFlow data)
    {
        FragmentLearnFlowItem frag  = new FragmentLearnFlowItem();
        Bundle args                 = new Bundle();

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

        TextView titleTextView  = (TextView)rootView.findViewById(R.id.title);
        TextView topTextView    = (TextView)rootView.findViewById(R.id.top_text);
        TextView bottomTextView = (TextView)rootView.findViewById(R.id.bottom_text);
        DataItemLearnFlow data  = (DataItemLearnFlow)getArguments().getSerializable(KEY_DATA);

        // display a title if there is one
        if (TextUtils.isEmpty(data.title))
        {
            titleTextView.setVisibility(View.GONE);
        }
        else
        {
            titleTextView.setVisibility(View.VISIBLE);
            titleTextView.setText(data.title);
        }

        topTextView.setText(data.topText);
        bottomTextView.setText(data.bottomText);

        return rootView;
    }
}
