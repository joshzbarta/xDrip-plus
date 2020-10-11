package com.eveningoutpost.dexdrip;

// jamorham

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(final Context baseContext) {
        final Context context = xdrip.getLangContext(baseContext);
        super.attachBaseContext(context);
    }

}
