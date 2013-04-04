package com.zman2245.pinpin.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.zman2245.pinpin.R;
import com.zman2245.pinpin.adapter.list.AdapterListLearn;
import com.zman2245.pinpin.data.DataItemLearnFlow;
import com.zman2245.pinpin.fragment.learn.FragmentLearnFlow;
import com.zman2245.pinpin.util.content.UtilContentStrings;

/**
 * A fragment for the list of learning sections
 * 
 * @author zack
 */
public class FragmentMainLearn extends Fragment
{
    /**
     * Use this method to create a new instance because the system will use the
     * default empty constructor if it needs to re-create the fragment. The
     * state of the fragment is saved (get/setArguments()), so put any init data
     * in the arguments.
     * 
     * @return A new instance of FragmentMainLearn
     */
    public static FragmentMainLearn newInstance()
    {
        return new FragmentMainLearn();
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
        View rootView = inflater.inflate(R.layout.fragment_main_learn, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.list);
        final AdapterListLearn adapter = new AdapterListLearn(getActivity(), inflater);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                navigateToLearnSection(position);
            }
        });

        return rootView;
    }

    private void navigateToLearnSection(int index)
    {
        DataItemLearnFlow[] datas = UtilContentStrings.getLearnSectionData(index);

        FragmentLearnFlow frag = FragmentLearnFlow.newInstance(datas, index == 0);
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.container, frag, "learn_flow");
        ft.addToBackStack("learn_flow");
        ft.commit();
    }
}
