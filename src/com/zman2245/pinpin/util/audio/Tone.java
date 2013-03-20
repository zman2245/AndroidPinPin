package com.zman2245.pinpin.util.audio;

import com.zman2245.pinpin.R;

/**
 * Pinyin Tones
 *
 * @author zack
 */
public enum Tone
{
    FIRST(0, "_1", R.array.reference_first),
    SECOND(1, "_2", R.array.reference_second),
    THIRD(2, "_3", R.array.reference_third),
    FOURTH(3, "_4", R.array.reference_fourth);

    public int index;
    public String fileExt;
    public int refResId;

    private Tone(int index, String ext, int refResId)
    {
    	this.index   = index;
    	this.fileExt  = ext;
        this.refResId = refResId;
    }
}
