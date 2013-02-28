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
    private static final int INDEX_TOP_STRINGS      = 0;
    private static final int INDEX_BOTTOM_STRINGS   = 1;
    private static final int INDEX_BUTTON_STRINGS   = 2;

    public static DataItemLearnFlow[] getLearnSectionData(int pos)
    {
        long ts = System.currentTimeMillis();

        String[] topStrings;
        String[] bottomStrings;
        TypedArray arraysLearnContent;
        TypedArray sectionTa;
        TypedArray sectionButtons = null;

        arraysLearnContent = AppPinPin.getTypedArray(R.array.arrays_learn_content);
        int resId = arraysLearnContent.getResourceId(pos, 0);
        sectionTa = AppPinPin.getTypedArray(resId);

        resId = sectionTa.getResourceId(INDEX_TOP_STRINGS, 0);
        topStrings = arraysLearnContent.getResources().getStringArray(resId);

        resId = sectionTa.getResourceId(INDEX_BOTTOM_STRINGS, 0);
        bottomStrings = arraysLearnContent.getResources().getStringArray(resId);

        if (sectionTa.length() > 2)
        {
            resId = sectionTa.getResourceId(INDEX_BUTTON_STRINGS, 0);
            sectionButtons = AppPinPin.getTypedArray(resId);
        }

        int len = topStrings.length;
        DataItemLearnFlow[] datas = new DataItemLearnFlow[len];
        for (int i = 0; i < len; i++)
        {
            DataItemLearnFlow data = new DataItemLearnFlow();

            data.topText    = topStrings[i];
            data.bottomText = bottomStrings[i];

            data.syllables  = getButtonsMatrix(sectionButtons, i);

            datas[i] = data;
        }

        datas[0].title = AppPinPin.getStringArray(R.array.strings_learn_list_titles)[pos];

        Log.d("TESTING", "get content time taken: " + (System.currentTimeMillis() - ts));

        // Important to recycle!
        arraysLearnContent.recycle();
        sectionTa.recycle();
        sectionButtons.recycle();

        return datas;
    }

    private static String[][] getButtonsMatrix(TypedArray sectionButtons, int pos)
    {
        if (sectionButtons == null)
            return null;

        int resId = sectionButtons.getResourceId(pos, 0);

        // there may be no buttons at this index
        if (resId == 0)
            return null;

        TypedArray pageButtons  = AppPinPin.getTypedArray(resId);

        int numRows         = pageButtons.length();
        int lenOfFirstRow   = 0;
        String[][] val      = new String[numRows][];

        for (int i = 0; i < numRows; i++)
        {
            resId   = pageButtons.getResourceId(i, 0);
            val[i]  = pageButtons.getResources().getStringArray(resId);

            // make sure all rows have the same length
            if (i == 0)
                lenOfFirstRow = val[i].length;
            if (val[i].length != lenOfFirstRow)
                throw new IllegalArgumentException("Length of all rows must be the same! Length of row 0: " + lenOfFirstRow + ". Length of row " + i + ": " + val[i].length);
        }

        // Important to recycle!
        pageButtons.recycle();

        return val;
    }
}
