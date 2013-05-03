package com.zman2245.pinpin.fragment.dialog;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.vending.billing.IInAppBillingService;
import com.zman2245.pinpin.appstate.InAppPurchasesModel;

/**
 * A dialog for upgrade purchases
 * 
 * @author zfoster
 */
public class DialogUpgrade extends DialogFragment
{
    private IInAppBillingService mService;

    private final String mNoAdProdId = "";
    private final String mQuizzesProdId = "";
    private String mNoAdPrice = "";
    private String mQuizzesPrice = "";

    public static DialogUpgrade newInstance(IInAppBillingService service)
    {
        DialogUpgrade dialog = new DialogUpgrade();

        dialog.mService = service;

        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // TODO
        return null;
    }

    @Override
    public void onDestroyView()
    {
        if (getDialog() != null && getRetainInstance())
        {
            // Work around for a bug in the support library. However, this still
            // does not seem to fix the dialog dismissing during orientation
            // change. See:
            // http://stackoverflow.com/questions/8235080/fragments-dialogfragment-and-screen-rotation
            getDialog().setDismissMessage(null);
            getDialog().setOnDismissListener(null);
        }

        super.onDestroyView();
    }

    private class QuerySkuInfoAsyncTask extends AsyncTask<Object, Object, Object>
    {
        @Override
        protected Object doInBackground(Object... params)
        {
            ArrayList<String> skuList = new ArrayList<String>();
            skuList.add(InAppPurchasesModel.PURCHASE_AD_FREE);
            skuList.add(InAppPurchasesModel.PURCHASE_QUIZZES);
            Bundle querySkus = new Bundle();
            querySkus.putStringArrayList("ITEM_ID_LIST", skuList);

            try
            {
                Bundle skuDetails = mService.getSkuDetails(3, getActivity().getPackageName(), "inapp", querySkus);

                int response = skuDetails.getInt("RESPONSE_CODE");
                if (response == 0)
                {
                    ArrayList<String> responseList = skuDetails.getStringArrayList("DETAILS_LIST");

                    for (String thisResponse : responseList)
                    {
                        JSONObject object = new JSONObject(thisResponse);
                        String sku = object.getString("productId");
                        String price = object.getString("price");
                        if (sku.equals(InAppPurchasesModel.PURCHASE_AD_FREE))
                        {
                            mNoAdPrice = price;
                        }
                        else if (sku.equals(InAppPurchasesModel.PURCHASE_QUIZZES))
                        {
                            mQuizzesPrice = price;
                        }
                    }
                }
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object result)
        {
            // TODO
        }
    }

    private class QueryPurchasedInfoAsyncTask extends AsyncTask<Object, Object, Object>
    {
        @Override
        protected Object doInBackground(Object... params)
        {
            try
            {
                Bundle ownedItems = mService.getPurchases(3, getActivity().getPackageName(), "inapp", null);

                int response = ownedItems.getInt("RESPONSE_CODE");
                if (response == 0)
                {
                    ArrayList<String> ownedSkus = ownedItems.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                }
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object result)
        {
            // TODO
        }
    }

    private class PurchaseItemAsyncTask extends AsyncTask<String, Object, Object>
    {
        @Override
        protected Object doInBackground(String... params)
        {
            String prodId = params[0];

            try
            {
                Bundle buyIntentBundle = mService.getBuyIntent(3, getActivity().getPackageName(), prodId, "inapp", "");

                PendingIntent pendingIntent = buyIntentBundle.getParcelable("BUY_INTENT");

                getActivity().startIntentSenderForResult(pendingIntent.getIntentSender(),
                        InAppPurchasesModel.PURCHASE_ACTIVITY_REQUEST_CODE, new Intent(), Integer.valueOf(0), Integer.valueOf(0),
                        Integer.valueOf(0));
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
            catch (SendIntentException e)
            {
                e.printStackTrace();
            }

            // is this right? let the activity handle from here on
            dismiss();

            return null;
        }

        @Override
        protected void onPostExecute(Object result)
        {
            // TODO
        }
    }
}
