package com.zman2245.pinpin.actionprovider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.actionbarsherlock.view.ActionProvider;
import com.actionbarsherlock.view.SubMenu;
import com.zman2245.pinpin.R;

/**
 * Action Provider for navigation selection list
 *
 * @author zack
 */
public class ActionProviderGlobalNavigation extends ActionProvider
{
    Context mContext;

    public ActionProviderGlobalNavigation(Context context)
    {
        super(context);

        mContext = context;
    }

    @Override
    public View onCreateActionView()
    {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.actionprovider_global_navigation, null);

        return view;
    }

    @Override
    public void onPrepareSubMenu(SubMenu subMenu)
    {
        subMenu.clear();

        subMenu.add("Learn");
    }
}
