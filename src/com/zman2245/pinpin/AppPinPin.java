package com.zman2245.pinpin;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.app.Application;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.DisplayMetrics;
import android.util.Log;

import com.zman2245.pinpin.appstate.ProgressFactory;
import com.zman2245.pinpin.util.audio.AudioResourceMapper;
import com.zman2245.pinpin.util.audio.AudioResourceMapperImpl;
import com.zman2245.pinpin.util.content.UtilQuizGenerator;
import com.zman2245.pinpin.xml.XmlParserSounds;

/**
 * The Pin Pin application!
 *
 * @author zack
 */
public class AppPinPin extends Application
{
    private static AppPinPin sInstance;
    private final AudioResourceMapper mAudioMapper;
    public static HashMap<String, Object> sSoundMap;
    public static HashMap<String, String> sSoundMapReverse; // maps each sound to a parent, non-toned sound that can then be looked up in sSoundMap
    public static UtilQuizGenerator sQuizGenerator;

    public AppPinPin()
    {
        super();

        sInstance = this;
        mAudioMapper = new AudioResourceMapperImpl();
        sQuizGenerator = new UtilQuizGenerator();
    }

    @Override
    public void onCreate()
    {
        Registry.sProgressFactory = new ProgressFactory(this, getSharedPreferences("appstate", 0));

        dumpDeviceInfo();

        // TODO: find somewhere to put this
        if (AppPinPin.sSoundMap == null)
        {
            try
            {
                InputStream is = getAssets().open("sounds.xml");
                XmlParserSounds parser = new XmlParserSounds();
                long ts = System.currentTimeMillis();
                HashMap<String, Object> soundsMap = parser.parse(is);
                Log.d("TESTING", "to parse the xml took this many milliseconds: " + (System.currentTimeMillis() - ts));
                HashMap<String, Object> tmp = (HashMap<String, Object>)soundsMap.get("an");
                String[] titles = (String[])tmp.get("title");
                Log.d("TESTING", "title: " + titles);
                AppPinPin.sSoundMap = soundsMap;

                buildReverseSoundMap();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
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

    private void dumpDeviceInfo()
    {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        String density = "";

        switch (metrics.densityDpi)
        {
        case DisplayMetrics.DENSITY_HIGH:
            density = "hdpi";
            break;
        case DisplayMetrics.DENSITY_LOW:
            density = "ldpi";
            break;
        case DisplayMetrics.DENSITY_MEDIUM:
            density = "mdpi";
            break;
        case DisplayMetrics.DENSITY_TV:
            density = "tvdpi";
            break;
        case DisplayMetrics.DENSITY_XHIGH:
            density = "xhdpi";
            break;
        case DisplayMetrics.DENSITY_XXHIGH:
            density = "xxhdpi";
            break;
        }

        Log.d("DeviceInfo", "density = " + density + ". dimensions = " + metrics.widthPixels + " x " + metrics.heightPixels);
        Log.d("DeviceInfo", "scale = " + metrics.density);
    }

    private void buildReverseSoundMap()
    {
        sSoundMapReverse = new HashMap<String, String>();

        Iterator<Map.Entry<String, Object>> it = sSoundMap.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry<String, Object> pairs = it.next();

            HashMap<String, Object> map = (HashMap<String, Object>)pairs.getValue();
            String[] titles             = (String[])map.get("title");

            for (String title : titles)
            {
                sSoundMapReverse.put(title, pairs.getKey());

                System.out.println("Adding following entry to reverse sound map: " + title + " . " + pairs.getKey());
            }
        }
    }
}
