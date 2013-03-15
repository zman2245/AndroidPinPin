package com.zman2245.pinpin.actionprovider;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.actionbarsherlock.view.ActionProvider;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.actionbarsherlock.view.SubMenu;
import com.zman2245.pinpin.ActivityLearn;
import com.zman2245.pinpin.ActivityQuiz;
import com.zman2245.pinpin.ActivityReference;
import com.zman2245.pinpin.R;

/**
 * Action Provider for navigation selection list
 *
 * @author zack
 */
public class ActionProviderGlobalNavigation extends ActionProvider
    implements OnMenuItemClickListener
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
        // view in the menu xml is sufficient
        return null;
    }

    @Override
    public void onPrepareSubMenu(SubMenu subMenu)
    {
        int size = subMenu.size();
        for (int i = 0; i < size; i++)
        {
            subMenu.getItem(i).setOnMenuItemClickListener(this);
        }
    }

    @Override
    public boolean hasSubMenu()
    {
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item)
    {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        switch (item.getItemId())
        {
            case R.id.menu_item_global_nav_learn:
                intent.setClass(mContext, ActivityLearn.class);
                break;

            case R.id.menu_item_global_nav_quiz:
                intent.setClass(mContext, ActivityQuiz.class);
                break;

            case R.id.menu_item_global_nav_reference:
                intent.setClass(mContext, ActivityReference.class);
                break;

            default:
                throw new IllegalArgumentException("Id not handled (id = " + item.getItemId() + ", text = " + item.getTitle() + ")");
        }

        mContext.startActivity(intent);

        return true;
    }
}
