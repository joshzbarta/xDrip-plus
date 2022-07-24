package com.eveningoutpost.dexdrip.insulin;


import static com.google.common.truth.Truth.assertWithMessage;
import static java.nio.file.FileVisitResult.CONTINUE;

import com.eveningoutpost.dexdrip.R;
import com.eveningoutpost.dexdrip.RobolectricTestWithConfig;
import com.eveningoutpost.dexdrip.xdrip;

import org.junit.Assert;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import lombok.val;

public class InsulinManagerTest extends RobolectricTestWithConfig {
    @Test
    public void testLoadStuff() throws IOException {
        ArrayList<Insulin> profiles = InsulinManager.getAllProfiles();

        for (Insulin p: profiles) {
            String x = p.toString();
        }
    }

}
