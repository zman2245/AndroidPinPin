package com.zman2245.pinpin.data;

import java.io.Serializable;

/**
 * Data to be presented on a learn flow page
 *
 * @author zack
 */
public class DataItemLearnFlow implements Serializable
{
    private static final long serialVersionUID = 1L;

    public String title         = "";

    public String topText       = "";
    public String bottomText    = "";

    public String[][] syllables = null;
}
