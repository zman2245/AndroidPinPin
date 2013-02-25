package com.zman2245.pinpin.util;

import android.content.res.TypedArray;
import android.util.Log;

import com.zman2245.pinpin.AppPinPin;
import com.zman2245.pinpin.R;
import com.zman2245.pinpin.data.DataItemLearnFlow;


/**
 * Contains static helper methods for accessing content from string resources
 *
 * @author zack
 */
public class UtilContentStrings
{
    public static DataItemLearnFlow[] getLearnSectionData(int pos)
    {
        long ts = System.currentTimeMillis();

        String[] topStrings;
        String[] bottomStrings;

        TypedArray ta = AppPinPin.getTypedArray(R.array.arrays_learn_content);
        int resId = ta.getResourceId(pos, 0);
        TypedArray sectionTa = AppPinPin.getTypedArray(resId);

        resId = sectionTa.getResourceId(0, 0);
        topStrings = ta.getResources().getStringArray(resId);

        resId = sectionTa.getResourceId(1, 0);
        bottomStrings = ta.getResources().getStringArray(resId);

        // Important to recycle!
        ta.recycle();
        sectionTa.recycle();

        int len = topStrings.length;
        DataItemLearnFlow[] datas = new DataItemLearnFlow[len];
        for (int i = 0; i < len; i++)
        {
            DataItemLearnFlow data = new DataItemLearnFlow();

            data.topText    = topStrings[i];
            data.bottomText = bottomStrings[i];

            datas[i] = data;
        }

        datas[0].title = AppPinPin.getStringArray(R.array.strings_learn_list_titles)[pos];

        Log.d("TESTING", "get content time taken: " + (System.currentTimeMillis() - ts));

        return datas;
    }

    public static String[] getLearnBottomStrings(int pos)
    {
        return null;
    }
}
