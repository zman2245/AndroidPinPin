package com.zman2245.pinpin;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.android.vending.billing.IInAppBillingService;
import com.zman2245.pinpin.appstate.InAppPurchasesModel;
import com.zman2245.pinpin.fragment.FragmentPurchasedQuery;
import com.zman2245.pinpin.fragment.PinBaseFragment;
import com.zman2245.pinpin.fragment.event.Event;
import com.zman2245.pinpin.fragment.event.FragmentEventListener;
import com.zman2245.pinpin.fragment.tab.FragmentTabLearn;
import com.zman2245.pinpin.fragment.tab.FragmentTabQuiz;
import com.zman2245.pinpin.fragment.tab.FragmentTabReference;
import com.zman2245.pinpin.fragment.tab.TabListener;

/**
 * "Top-level" sections including Learn, Quiz, and Reference Fragments
 * 
 * @author zack
 */
public class MainSectionsActivity extends SherlockFragmentActivity implements FragmentEventListener
{
    public static String INAPP_LOG_TAG = "InApp";

    // store the active tab here
    public static String ACTIVE_TAB = "activeTab";
    FragmentPurchasedQuery mPurchasedQueryFragment;

    // For in-app billing
    private IInAppBillingService mService;
    private final ServiceConnection mServiceConn = new ServiceConnection()
    {
        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            Log.d(INAPP_LOG_TAG, "onServiceDisconnected");
            mService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            Log.d(INAPP_LOG_TAG, "onServiceConnected");

            mService = IInAppBillingService.Stub.asInterface(service);

            // purchased query fragment
            mPurchasedQueryFragment = (FragmentPurchasedQuery)getSupportFragmentManager().findFragmentByTag("purchased");
            if (mPurchasedQueryFragment == null)
                getPurchased();
            // update AppState! TODO
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_sections);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Tab tab1 = actionBar.newTab().setText(R.string.menu_global_nav_quiz)
                .setTabListener(new TabListener<FragmentTabQuiz>(this, "maintab-quiz", FragmentTabQuiz.class));

        actionBar.addTab(tab1, true);

        Tab tab2 = actionBar.newTab().setText(R.string.menu_global_nav_learn)
                .setTabListener(new TabListener<FragmentTabLearn>(this, "maintab-learn", FragmentTabLearn.class));

        actionBar.addTab(tab2);

        Tab tab3 = actionBar.newTab().setText(R.string.menu_global_nav_reference)
                .setTabListener(new TabListener<FragmentTabReference>(this, "maintab-reference", FragmentTabReference.class));

        actionBar.addTab(tab3);

        // check if there is a saved state to select active tab
        if (savedInstanceState != null)
            getSupportActionBar().setSelectedNavigationItem(savedInstanceState.getInt(ACTIVE_TAB));

        // For in-app billing
        bindService(new Intent("com.android.vending.billing.InAppBillingService.BIND"), mServiceConn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        // save active tab
        outState.putInt(ACTIVE_TAB, getSupportActionBar().getSelectedNavigationIndex());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed()
    {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentByTag("maintab");

        if (frag != null && frag instanceof PinBaseFragment)
        {
            if (((PinBaseFragment) frag).onBackPressed())
                return;
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.menu_default, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
        case R.id.menu_item_upgrade:
            // TODO: pass in mService or if mservice still null, give loading indicator or error show an error
            return true;
        }

        return false;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        if (mServiceConn != null)
        {
            unbindService(mServiceConn);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == InAppPurchasesModel.PURCHASE_ACTIVITY_REQUEST_CODE)
        {
            int responseCode = data.getIntExtra("RESPONSE_CODE", 0);
            String purchaseData = data.getStringExtra("INAPP_PURCHASE_DATA");
            String dataSignature = data.getStringExtra("INAPP_DATA_SIGNATURE");

            if (resultCode == RESULT_OK)
            {
                try
                {
                    JSONObject jo = new JSONObject(purchaseData);
                    String sku = jo.getString("productId");
                    Log.d("TESTING", "You have bought the " + sku + ". Excellent choice, adventurer!");
                }
                catch (JSONException e)
                {
                    Log.d("TESTING", "Failed to parse purchase data.");
                    e.printStackTrace();
                }
            }
        }
    }

    // FragmentEventListener impl

    @Override
    public void handleEvent(Event event)
    {
        switch (event.type)
        {
        case INAPP_PURCHASED:
            ArrayList<String> purchasedProdIds = (ArrayList<String>)event.data.get("purchased");
            break;

        case INAPP_PURCHASED_FAILED:
            // TODO:
            break;

        default:
        }
    }

    // private helpers

    private void getPurchased()
    {
        mPurchasedQueryFragment = FragmentPurchasedQuery.newInstance(mService);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(mPurchasedQueryFragment, "purchased");
        ft.commit();
    }
}
