package com.zman2245.pinpin;

import android.app.Application;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.zman2245.pinpin.util.audio.AudioResourceMapper;
import com.zman2245.pinpin.util.audio.AudioResourceMapperImpl;

/**
 * The Pin Pin application!
 *
 * @author zack
 */
public class AppPinPin extends Application
{
    private static AppPinPin sInstance;
    private final AudioResourceMapper mAudioMapper;

    public AppPinPin()
    {
        super();

        sInstance = this;
        mAudioMapper = new AudioResourceMapperImpl();
    }

    public static AppPinPin getInstance()
    {
        return sInstance;
    }

    public static Resources getAppResources()
    {
        return sInstance.getResources();
    }

    public static String[] getStringArray(int id)
    {
        return sInstance.getResources().getStringArray(id);
    }

    public static TypedArray getTypedArray(int id)
    {
        return sInstance.getResources().obtainTypedArray(id);
    }

    public static AudioResourceMapper getAudioMapper()
    {
        return sInstance.mAudioMapper;
    }
}
