package com.eveningoutpost.dexdrip;

// jamorham

import android.app.Activity;
import android.app.ListActivity;
import androidx.fragment.app.ListFragment;
//import androidx.fragment.app.ListFragment;


import android.content.Context;
import android.view.View;


public abstract class BaseListActivity extends ListFragment {

    /*
    @Override
    protected void attachBaseContext(final Context baseContext) {
        final Context context = xdrip.getLangContext(baseContext);
        //super.attachBaseContext(context);
    }
    */

    public Context getApplicationContext()
    {
        return this.getApplicationContext();
    }

    public View findViewById(int id)
    {
        return getActivity().findViewById(id);
    }
}
