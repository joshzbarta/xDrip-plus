package com.eveningoutpost.dexdrip.Tables;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import androidx.drawerlayout.widget.DrawerLayout;
import android.view.View;
import android.widget.SimpleCursorAdapter;

import com.activeandroid.Cache;
import com.eveningoutpost.dexdrip.BaseListActivity;
import com.eveningoutpost.dexdrip.NavigationDrawerFragment;
import com.eveningoutpost.dexdrip.R;

import java.util.ArrayList;


public class SensorDataTable extends BaseListActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private String menu_name = "Sensor Data Table";
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private ArrayList<String> results = new ArrayList<String>();
    private View mRootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Activity parentActivity = getActivity();

        parentActivity.setTheme(R.style.OldAppTheme); // or null actionbar
        super.onCreate(savedInstanceState);
        parentActivity.setContentView(R.layout.raw_data_list);
    }

    @Override
    public void onResume(){
        super.onResume();
        mNavigationDrawerFragment = (NavigationDrawerFragment) getParentFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), menu_name, this.getContext());
        getData();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        mNavigationDrawerFragment.swapContext(position);
    }

    private void getData() {
        Cursor cursor = Cache.openDatabase().rawQuery("Select * from Sensors order by _ID desc", null);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this.getContext(),
                R.layout.raw_data_list_item,
                cursor,
                new String[] { "started_at", "latest_battery_level", "uuid", "uuid" },
                new int[] { R.id.raw_data_id, R.id.raw_data_value , R.id.raw_data_slope, R.id.raw_data_timestamp });

        this.setListAdapter(adapter);
//        ListView listView = (ListView) findViewById(R.id.list);
//        listView.setAdapter(adapter);
    }

}
