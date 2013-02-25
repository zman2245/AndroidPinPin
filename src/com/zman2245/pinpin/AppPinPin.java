package com.zman2245.pinpin;

import android.app.Application;

/**
 * The Pin Pin application!
 *
 * @author zack
 */
public class AppPinPin extends Application
{
    private static AppPinPin sInstance;

    public AppPinPin()
    {
        super();

        sInstance = this;
    }

    public static String[] getStringArray(int id)
    {
        return sInstance.getResources().getStringArray(id);
    }
}
