package com.eveningoutpost.dexdrip.dagger;

import com.eveningoutpost.dexdrip.ZHome;
import com.eveningoutpost.dexdrip.ui.ZHomeShelfModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jamorham on 20/09/2017.
 *
 * Interface requires method for every concrete class it is called from
 *
 */

@Singleton
@Component(modules = {ZHomeShelfModule.class})
public interface ZHomeShelfComponent {

    void inject(ZHome target);

}

