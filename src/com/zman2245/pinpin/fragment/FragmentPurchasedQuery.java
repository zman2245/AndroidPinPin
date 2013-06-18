package com.zman2245.pinpin.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;

import com.android.vending.billing.IInAppBillingService;
import com.zman2245.pinpin.fragment.event.Event;
import com.zman2245.pinpin.fragment.event.EventType;

/**
 * Wraps a query for getting in-app purchases made by the user
 *
 * @author zfoster
 */
public class FragmentPurchasedQuery extends PinBaseFragment
{
    private IInAppBillingService mService;

    public static FragmentPurchasedQuery newInstance()
    {
        FragmentPurchasedQuery frag = new FragmentPurchasedQuery();
        return frag;
    }

    public void setInAppBillingService(IInAppBillingService service)
    {
        mService = service;
    }

    public void getPurchased(String packageName)
    {
        QueryPurchasedInfoAsyncTask task = new QueryPurchasedInfoAsyncTask();
        task.execute(packageName);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    private class QueryPurchasedInfoAsyncTask extends AsyncTask<Object, Object, ArrayList<String>>
    {
        @Override
        protected ArrayList<String> doInBackground(Object... params)
        {
            try
            {
                if (mService == null)
                    return null;

                Bundle ownedItems = mService.getPurchases(3, (String)params[0], "inapp", null);

                int response = ownedItems.getInt("RESPONSE_CODE");
                if (response == 0)
                {
                    ArrayList<String> ownedSkus = ownedItems.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                    return ownedSkus;
                }
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result)
        {
            if (result == null)
            {
                if (mService != null)
                    handleEvent(new Event(EventType.INAPP_PURCHASED_FAILED));
            }
            else
            {
                HashMap<String, Object> data = new HashMap<String, Object>();
                data.put("purchased", result);
                handleEvent(new Event(EventType.INAPP_PURCHASED, data));
            }
        }
    }
}
