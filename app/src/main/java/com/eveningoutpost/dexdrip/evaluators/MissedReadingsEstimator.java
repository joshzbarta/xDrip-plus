package com.eveningoutpost.dexdrip.evaluators;

// jamorham

import com.eveningoutpost.dexdrip.models.BgReading;
import com.eveningoutpost.dexdrip.models.JoH;
import com.eveningoutpost.dexdrip.utilityModels.Constants;

import static com.eveningoutpost.dexdrip.utilityModels.BgGraphBuilder.DEXCOM_PERIOD;

public class MissedReadingsEstimator {

    public static int estimate() {

        final BgReading bgReading = BgReading.last();
        final long since = bgReading != null ? JoH.msSince(bgReading.timestamp) : Constants.DAY_IN_MS;
        return (int) (since / DEXCOM_PERIOD);
    }

}
