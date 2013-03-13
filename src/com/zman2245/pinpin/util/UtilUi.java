package com.zman2245.pinpin.util;

import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;

/**
 * Utility functions for ui components
 *
 * @author zack
 */
public class UtilUi
{
    /**
     * Apparently wrap_content doesn't work with GridView:
     * http://stackoverflow.com/questions/11870077/android-how-to-force-gridview-width-to-wrap-content
     *
     * So this method sets the width of the GridView manually :(
     *
     */
    public static void fixGridViewWidth(GridView gv, int numCols, int cellWidth)
    {
        LayoutParams lps = gv.getLayoutParams();
        lps.width = (numCols * cellWidth);
        gv.setLayoutParams(lps);
    }
}
