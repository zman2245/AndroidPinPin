package com.zman2245.pinpin.adapter.grid;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zman2245.pinpin.AppPinPin;
import com.zman2245.pinpin.R;

public class AdapterGridReference extends BaseAdapter
{

    @Override
    public int getCount()
    {
        int numRows = AppPinPin.getAppResources().getInteger(R.integer.reference_num_rows);
        int numCols = AppPinPin.getAppResources().getInteger(R.integer.reference_num_columns);

        return numRows * numCols;
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
