package com.zman2245.pinpin.util.audio;

import com.zman2245.pinpin.R;

/**
 * Pinyin Tones
 *
 * @author zack
 */
public enum Tone
{
    FIRST(R.array.reference_first), SECOND(R.array.reference_second), THIRD(R.array.reference_third), FOURTH(R.array.reference_fourth);

    public int refResId;

    private Tone(int refResId)
    {
        this.refResId = refResId;
    }
}
