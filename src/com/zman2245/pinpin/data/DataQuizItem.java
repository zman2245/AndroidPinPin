package com.zman2245.pinpin.data;

import java.io.Serializable;

/**
 * Data to be presented on a quiz question
 *
 * @author zack
 */
public class DataQuizItem implements Serializable
{
    private static final long serialVersionUID = 1L;

    public String correctAnswer = "";
    public String[] answers     = {};
    public int correctIndex     = -1;
}
