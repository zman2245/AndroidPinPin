package com.zman2245.pinpin.appstate;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.zman2245.pinpin.R;
import com.zman2245.pinpin.data.DataItemProgress;
import com.zman2245.pinpin.util.content.CompatUtil;

/**
 * Handles saving/restoring application state
 * 
 * For example, progress statuses for quiz and learn sections
 * 
 * @author zfoster
 */
public class AppState
{
    private final SharedPreferences mPrefs;

    private InAppPurchasesModel mPurchasesModel;

    private final HashMap<String, DataItemProgress> mLearnProgresses;
    private final HashMap<String, DataItemProgress> mQuizProgresses;

    public AppState(Context context, SharedPreferences prefs)
    {
        mPrefs = prefs;

        mLearnProgresses = new HashMap<String, DataItemProgress>();
        mQuizProgresses = new HashMap<String, DataItemProgress>();

        // build
        String[] learnTitles = context.getResources().getStringArray(R.array.strings_learn_list_titles);
        for (String title : learnTitles)
        {
            String jsonEncodedData = prefs.getString(title, "");
            DataItemProgress data = fromJson(jsonEncodedData);
            mLearnProgresses.put(title, data);
        }
    }

    public void markLearnProgress(String title, int lastItem, int totalItems)
    {
        DataItemProgress data = mLearnProgresses.get(title);

        if (data == null)
            throw new IllegalArgumentException("title (" + title + ") not found in the learn progress map.");

        if (lastItem > data.last_item_completed)
        {
            Editor editor = mPrefs.edit();

            data.last_item_completed = lastItem;
            data.total_items = totalItems;
            data.completed = (lastItem >= (totalItems - 1));

            editor.putString(title, toJsonString(data));
            CompatUtil.editorApply(editor);
        }
    }

    public DataItemProgress getLearnProgress(String title)
    {
        DataItemProgress data = mLearnProgresses.get(title);

        if (data == null)
            throw new IllegalArgumentException("title not found in the learn progress map");

        return data;
    }

    // private helpers

    private String toJsonString(DataItemProgress item)
    {
        JSONObject json = new JSONObject();

        try
        {
            json.put("completed", item.completed);
            json.put("last_item_completed", item.last_item_completed);
            json.put("total_items", item.total_items);
            json.put("num_correct", item.num_correct);
            json.put("num_wrong", item.num_wrong);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return json.toString();
    }

    private DataItemProgress fromJson(String jsonString)
    {
        DataItemProgress data = new DataItemProgress();

        if (TextUtils.isEmpty(jsonString))
            return data;

        try
        {
            JSONObject json = new JSONObject(jsonString);

            data.completed = json.getBoolean("completed");
            data.last_item_completed = json.getInt("last_item_completed");
            data.total_items = json.getInt("total_items");
            data.num_correct = json.getInt("num_correct");
            data.num_wrong = json.getInt("num_wrong");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return data;
    }
}
