package io.github.charly1811.captions.util;


import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.github.charly1811.captions.util.TimeUtils;

import static junit.framework.Assert.assertEquals;

/**
 * Copyright 2015 Charles-Eugene Loubao
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class TimeUtilsTest {

    @Test
    public void testMillisToString_hour_minutes_seconds_milliseconds() throws Exception {
        long hoursMillis = TimeUnit.HOURS.toMillis(0);
        long minutesMillis = TimeUnit.MINUTES.toMillis(2);
        long secondsMillis = TimeUnit.SECONDS.toMillis(20);
        double millis = 375;

        assertEquals("02:20", TimeUtils.millisToString(hoursMillis + minutesMillis + secondsMillis + millis));
    }

    @Test
    public void testMillisToStringDouble_hour_minutes_seconds_milliseconds() throws Exception {
        long hoursMillis = TimeUnit.HOURS.toMillis(0);
        long minutesMillis = TimeUnit.MINUTES.toMillis(2);
        long secondsMillis = TimeUnit.SECONDS.toMillis(20);
        double millis = 375;

        assertEquals("00:02:20.375", TimeUtils.millisToStringDouble(hoursMillis + minutesMillis + secondsMillis + millis));
    }

    @Test
    public void testStringToMillis_hour_minutes_seconds_milliseconds() throws Exception {
        String time = "00:02:20.375";
        long hoursMillis = TimeUnit.HOURS.toMillis(0);
        long minutesMillis = TimeUnit.MINUTES.toMillis(2);
        long secondsMillis = TimeUnit.SECONDS.toMillis(20);
        double millis = 375;

        double expected = hoursMillis + minutesMillis + secondsMillis + millis;

        assertEquals(expected, TimeUtils.stringToMillis(time));
    }

    @Test
    public void testStringToMillis_hour_minutes_seconds_milliseconds_with_semicolon() throws Exception {
        String time = "00:02:20,375";

        long hoursMillis = TimeUnit.HOURS.toMillis(0);
        long minutesMillis = TimeUnit.MINUTES.toMillis(2);
        long secondsMillis = TimeUnit.SECONDS.toMillis(20);
        double millis = 375;

        double expected = hoursMillis + minutesMillis + secondsMillis + millis;

        assertEquals(expected, TimeUtils.stringToMillis(time));
    }
}