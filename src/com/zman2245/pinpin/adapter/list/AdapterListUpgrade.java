package com.zman2245.pinpin.adapter.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zman2245.pinpin.R;

/**
 * ListView adapter for the upgrade menu
 *
 * @author zack
 */
public class AdapterListUpgrade extends BaseAdapter
{
    private final Context        mContext;
    private final LayoutInflater mInflater;

    private final UpgradeData[] mData;

    // Constructor

    public AdapterListUpgrade(Context context, LayoutInflater inflater)
    {
        mContext    = context;
        mInflater   = inflater;

        mData = new UpgradeData[2];
        mData[0] = new UpgradeData();
        mData[0].icon = R.drawable.key_orange;
        mData[0].price = "$0.99";
        mData[0].title = "Unlock Quizzes";
        mData[0].subtitle = "Unlock all of the listening quizzes";
        mData[1] = new UpgradeData();
        mData[1].icon = R.drawable.key_orange;
        mData[1].price = "$0.99";
        mData[1].title = "Remove Ads";
        mData[1].subtitle = "Remove ads";
    }

    // BaseAdapter hooks

    @Override
    public int getCount()
    {
        return mData.length;
    }

    @Override
    public Object getItem(int position)
    {
        return mData[position];
    }

    @Override
    public long getItemId(int position)
    {
        // All are the same
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.item_list_upgrade, parent, false);
            holder = new ViewHolder();
            holder.mTitle       = (TextView) convertView.findViewById(R.id.title);
            holder.mSubtitle    = (TextView) convertView.findViewById(R.id.subtitle);
            holder.mPrice       = (TextView) convertView.findViewById(R.id.price);
            holder.mIcon        = (ImageView) convertView.findViewById(R.id.icon);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTitle.setText(mData[position].title);
        holder.mSubtitle.setText(mData[position].subtitle);
        holder.mPrice.setText(mData[position].price);
        holder.mIcon.setImageResource(mData[position].icon);

        return convertView;
    }

    // ViewHolder helper class

    private static class ViewHolder
    {
        TextView    mTitle;
        TextView    mSubtitle;
        TextView    mPrice;
        ImageView   mIcon;
    }

    // data class
    private static class UpgradeData
    {
        public String title;
        public String subtitle;
        public String price;
        public int icon;
    }
}
