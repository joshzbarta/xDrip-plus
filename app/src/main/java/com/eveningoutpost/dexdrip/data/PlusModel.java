package com.eveningoutpost.dexdrip.data;

import com.activeandroid.Model;
import com.activeandroid.util.SQLiteUtils;

/**
 * Created by jamorham on 01/02/2017.
 */

public class PlusModel extends Model {

    public synchronized static boolean fixUpTable(String[] schema, boolean patched) {
        if (patched) return true;

        for (String patch : schema) {
            try {
                SQLiteUtils.execSql(patch);
            } catch (Exception e) {
                //
            }
        }
        return true;
    }

}
