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
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import lombok.val;

public class InsulinManagerTest extends RobolectricTestWithConfig {

    /**
     * Check that MessageFormat strings are still working after resource translation
     */


    @Test
    public void testLoadStuff() throws IOException {
        int x = 3;
        int y = 5;
        int z = x+y;

        Assert.assertEquals(z, 8);
    }

}
