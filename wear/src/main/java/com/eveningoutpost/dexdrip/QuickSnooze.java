package com.eveningoutpost.dexdrip;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.eveningoutpost.dexdrip.models.JoH;
import com.eveningoutpost.dexdrip.utilityModels.AlertPlayer;

// jamorham

// Send a snooze request to silence any alarm. Designed to be bound to a button for fast access

public class QuickSnooze extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JoH.static_toast_long(getString(R.string.sending_snooze));
        AlertPlayer.getPlayer().Snooze(xdrip.getAppContext(), -1);
        finish();
    }

}
