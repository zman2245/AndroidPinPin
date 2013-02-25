package com.zman2245.pinpin.adapter.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zman2245.pinpin.R;

/**
 * ListView adapter for the main Learn section
 *
 * @author zack
 */
public class AdapterListLearn extends BaseAdapter
{
    private final LayoutInflater mInflater;

    private final String[] mTitles;
    private final String[] mSubtitles;

    public AdapterListLearn(Context context, LayoutInflater inflater)
    {
        mInflater = inflater;

        mTitles     = context.getResources().getStringArray(R.array.strings_learn_list_titles);
        mSubtitles  = context.getResources().getStringArray(R.array.strings_learn_list_bodies);

        if (mTitles.length != mSubtitles.length)
            throw new IllegalStateException("String arrays don't match. num titles: " + mTitles.length + ". num bodies: " + mSubtitles.length);
    }

    @Override
    public int getCount()
    {
        return mTitles.length;
    }

    @Override
    public Object getItem(int position)
    {
        // not needed
        return null;
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
            convertView         = mInflater.inflate(R.layout.item_list_learn, parent, false);
            holder              = new ViewHolder();
            holder.mTitle       = (TextView) convertView.findViewById(R.id.title);
            holder.mSubtitle    = (TextView) convertView.findViewById(R.id.subtitle);
//            holder.mSideBar = (ViewDna) convertView.findViewById(R.id.url_item_dna);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTitle.setText(mTitles[position]);
        holder.mSubtitle.setText(mSubtitles[position]);

        return convertView;
    }

    private static class ViewHolder
    {
        TextView mTitle;
        TextView mSubtitle;
        TextView mSideBar;
    }
}
