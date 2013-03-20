package com.zman2245.pinpin;

import java.io.InputStream;
import java.util.HashMap;

import android.app.Application;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;

import com.zman2245.pinpin.util.UtilQuizGenerator;
import com.zman2245.pinpin.util.audio.AudioResourceMapper;
import com.zman2245.pinpin.util.audio.AudioResourceMapperImpl;
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
    public static UtilQuizGenerator sQuizGenerator;

    public AppPinPin()
    {
        super();

        sInstance = this;
        mAudioMapper = new AudioResourceMapperImpl();
        sQuizGenerator = new UtilQuizGenerator();
    }
    
    @Override
    public void onCreate ()
    {
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
}
