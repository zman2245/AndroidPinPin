package com.zman2245.pinpin.util;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import com.zman2245.pinpin.AppPinPin;

/**
 * Helpers for playing audio
 *
 * @author zack
 */
public class UtilAudioPlayer
{
    /**
     * Play the sound given by the resId, which should be a raw mp3 resource
     *
     * @param resId
     */
    public static void playSound(int resId)
    {
        // TOOD: This likely needs to run on a background thread. start() I think needs to run on ui thread though
        MediaPlayer player = MediaPlayer.create(AppPinPin.getInstance().getApplicationContext(), resId);

        player.setLooping(false);
        player.setOnCompletionListener(new OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                mp.release();
            }
        });

        player.start();
    }
}
