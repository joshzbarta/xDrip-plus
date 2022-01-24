package com.eveningoutpost.dexdrip;

/**
 * Created by jamorham on 11/01/16.
 */

import android.content.Intent;

import com.google.firebase.messaging.FirebaseMessagingService;

public class MyInstanceIDListenerService extends FirebaseMessagingService {
    private static final String TAG = "jamorham MyInstanceIDLS";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. This call is initiated by the
     * InstanceID provider.
     */
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);

        // Fetch updated Instance ID token and notify our app's server of any changes (if applicable).
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
}