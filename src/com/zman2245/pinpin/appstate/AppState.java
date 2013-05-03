package com.zman2245.pinpin.appstate;

import java.util.HashMap;

import com.zman2245.pinpin.data.DataItemProgress;

/**
 * Handles saving/restoring application state
 * 
 * For example, progress statuses for quiz and learn sections
 * 
 * @author zfoster
 */
public class AppState
{
    private InAppPurchasesModel mPurchasesModel;

    private HashMap<String, DataItemProgress> mLearnProgresses;
    private HashMap<String, DataItemProgress> mQuizProgresses;

    public void persist()
    {

    }
}
