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
 * ListView adapter for the main Quiz section
 *
 * @author zack
 */
public class AdapterListQuiz extends BaseAdapter
{
    private final LayoutInflater mInflater;

    private final String[] mTitles;

    public AdapterListQuiz(Context context, LayoutInflater inflater)
    {
        mInflater = inflater;
        mTitles   = context.getResources().getStringArray(R.array.quiz_list_titles);
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
            convertView         = mInflater.inflate(R.layout.item_list_quiz, parent, false);
            holder              = new ViewHolder();
            holder.mTitle       = (TextView) convertView.findViewById(R.id.title);
            holder.mImage       = (ImageView) convertView.findViewById(R.id.image);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTitle.setText(mTitles[position]);

        return convertView;
    }

    private static class ViewHolder
    {
        TextView mTitle;
        ImageView mImage;
    }
}
